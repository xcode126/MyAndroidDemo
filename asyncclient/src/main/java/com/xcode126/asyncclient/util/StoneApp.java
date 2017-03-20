package com.xcode126.asyncclient.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.xcode126.asyncclient.BuildConfig;
import com.xcode126.asyncclient.net.Async;
import com.xcode126.asyncclient.net.ExecResult;
import com.xcode126.asyncclient.net.Task;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import luki.x.XConfig;
import luki.x.XParser;
import luki.x.base.IDataParser;
import luki.x.base.XLog;
import luki.x.util.DESUtil;

/**
 * StoneApp
 * Created by Luki on 15/3/9s.
 * Version:1
 */
public class StoneApp extends Application {
	private static final String TAG = "StoneApp";
	private static final ArrayList<Activity> activityLists = new ArrayList<>();
	private static StoneApp instance;
//	private ScreenObserver screenObserver;
//	private final static Gson gson = new GsonBuilder().create();

	public static StoneApp getApp() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		AppInfo.init(this);
		SDCardUtil.getInstance(this);
		CrashHandler crashHandler = CrashHandler.getInstance();
		// 注册CrashHandler
		crashHandler.init(getApplicationContext());

		if (Constants.DEBUG) {
			Log.e(TAG, "UMENG_APPKEY:" + AppUtil.getMetaData(this, "UMENG_APPKEY"));
			Log.e(TAG, "JPUSH_APPKEY:" + AppUtil.getMetaData(this, "JPUSH_APPKEY"));
			Log.e(TAG, "UMENG_CHANNEL:" + AppInfo.CHANNEL);
			Log.e(TAG, "JPUSH_CHANNEL:" + AppInfo.JCHANNEL);
			Log.e(TAG, "PORT:" + AppInfo.PORT);
			Log.e(TAG, "CLASS:" + AppInfo.class);
		}
		/*Unicorn.init(this, "029a500544d7e83567d2aa149fa94d11", options(), new UnicornImageLoader() {
			@Nullable
			@Override
			public Bitmap loadImageSync(String uri, int width, int height) {
				return null;
			}

			@Override
			public void loadImage(String uri, int width, int height, ImageLoaderListener listener) {

			}
		});*/

		XImageView.initImageLoader(this);

		XConfig.Builder builder = new XConfig.Builder(this);

		if (Constants.DEBUG) {
			builder.writeDebugLogs();
		}

		XConfig configuration = builder.cacheInDB(true)/*.enabledDefaultParserLogging(false)*/.taskDataParser(new IDataParser() {

			@Override
			public Object from(String result, Type clazz) throws Exception {
				try {
					if (!TextUtils.isEmpty(result) && !result.startsWith("{")) {
						result = DESUtil.decrypt(result, Constants.KEY);
						XLog.v(TAG, result);
					} else {
						JSONObject jsonObject = new JSONObject(result);
						if (jsonObject.has("resText")) {
							String resText = jsonObject.getString("resText");
							String isEnc = jsonObject.getString("isEnc");
							if ("Y".equals(isEnc)) {
								result = DESUtil.decrypt(resText, Constants.KEY);
								XLog.v(TAG, result);
								XLog.v(TAG, String.valueOf(clazz));
								return gson.fromJson(result, clazz);

							} else {
								return gson.fromJson(resText, clazz);
							}
						}
					}
					return gson.fromJson(result, clazz);
				} catch (Exception e) {
					try {
						return gson.fromJson(result, new TypeToken<ExecResult<String>>() {}.getType());
					} catch (Exception e2) {
						if (BuildConfig.DEBUG) {
							e.printStackTrace();
						}
						throw e;
					}

				}
			}
		}).build();

		XParser.INSTANCE.init(configuration);
		JPushInterface.setDebugMode(Constants.DEBUG);// true 设置开启日志，发布时请关闭日志(false)
		JPushInterface.init(this);// 初始化 JPush
		SharedPreferencesUtil.init(this);
		MobclickAgent.setDebugMode(Constants.DEBUG);
		MobclickAgent.openActivityDurationTrack(false);


		UserSession.init();

		screenObserver = new ScreenObserver(this);
		screenObserver.requestScreenStateUpdate(new ScreenObserver.ScreenStateListener() {
			@Override
			public void onScreenStateChange(boolean isScreenOn) {
				Activity activityTop = getActivityTop();
				if (activityTop instanceof BaseActivity) {
					BaseActivity ba = (BaseActivity) activityTop;
					ba.isScreenOn = isScreenOn;
					if (isScreenOn) {
						ba.onScreenOn();
					} else {
						ba.onScreenOff();
					}
				}
			}
		});

//			Async<String> task = new Async<>(StoneApp.this);
//			final IDBHelper dbHelper = XParser.INSTANCE.getDBHelper();
//			DBSelection<StatisticBean> dbSelection = new DBSelection<>();
//			dbSelection.selection = "1=1";
//			final List<StatisticBean> statisticBeanList = dbHelper.selectBySelection(StatisticBean.class, dbSelection);
//			Task.statistics(task, statisticBeanList, new TaskBack<String>() {
//
//				@Override
//				public void onSuccess(String result) {
//					dbHelper.delete(statisticBeanList);
//				}
//
//				/* (non-Javadoc)
//				 * @see cn.stlc.app.net.task.Async.TaskBack#onFailed(int, java.lang.String)
//				 */
//				@Override
//				public void onFailed(int code, String msg) {
////				super.onFailed(code, msg);
//				}
//			});
		SharedPreferencesUtil spu = SharedPreferencesUtil.getInstance();
		Boolean isFirstStart = spu.get(Key.IS_FIRST_START, Boolean.FALSE);
		if (!isFirstStart) {
			spu.put(Key.IS_FIRST_START, Boolean.TRUE);
			Task.appActivation(new Async<String>(this), null);
		}


		com.tendcloud.appcpa.TalkingDataAppCpa.init(this.getApplicationContext(), "8c557a75611c4325bb8e14303fa2ad2d", AppInfo.CHANNEL);
//		}
	}

	/**
	 * 获得当前进程号
	 *
	 * @param context context
	 * @return processName
	 */
	public static String getCurProcessName(Context context) {
		int pid = android.os.Process.myPid();
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
			if (appProcess.pid == pid) {
				return appProcess.processName;
			}
		}
		return null;
	}
/*	private YSFOptions options() {
		YSFOptions options = new YSFOptions();
		options.statusBarNotificationConfig = new StatusBarNotificationConfig();
		options.savePowerConfig = new SavePowerConfig();
		return options;
	}*/

	/* (non-Javadoc)
	 * @see android.app.Application#onTerminate()
	 */
	@Override
	public void onTerminate() {
		super.onTerminate();
		Log.e(TAG, "onTerminate");
		screenObserver.stopScreenStateUpdate();
	}

	/**
	 * 添加存储Activity
	 *
	 * @param activity activity
	 */
	public synchronized static void register(Activity activity) {
		for (int i = activityLists.size() - 1; i >= 0; i--) {
			Activity ac = activityLists.get(i);
			if (activity.getClass().getName().equals(ac.getClass().getName())) {
				activityLists.remove(ac);
				if (!ac.isFinishing()) {
					ac.finish();
				}
				break;
			}
		}
		activityLists.add(activity);
	}

	/**
	 * 杀死Activity
	 *
	 * @param activity activity
	 */
	public synchronized static void unRegister(Activity activity) {
		if (activity != null && activityLists != null && activityLists.size() != 0) {
			activityLists.remove(activity);
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}

	/**
	 * 根据类名获取所对应Activity
	 *
	 * @param name name
	 * @return activity
	 */
	public static Activity getActivityByName(String name) {
		for (int i = activityLists.size() - 1; i >= 0; i--) {
			Activity ac = activityLists.get(i);
			if (ac.isFinishing()) {
				continue;
			}
			if (ac.getClass().getName().contains(name)) {
				return ac;
			}
		}
		return null;
	}

	/**
	 * 根据类名获取所对应Activity
	 *
	 * @return activity
	 */
	public static Activity getActivityTop() {
		if (activityLists.isEmpty()) {
			return null;
		}
		return activityLists.get(activityLists.size() - 1);
	}

	/**
	 * 杀死所有Activity
	 */
	public static void exit() {
		MobclickAgent.onKillProcess(getApp());
		try {
			getApp().screenObserver.stopScreenStateUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (activityLists != null && activityLists.size() != 0) {
			for (Activity ac : activityLists) {
				if (!ac.isFinishing()) {
					ac.finish();
				}
			}
		}
		System.exit(0);
	}
}
