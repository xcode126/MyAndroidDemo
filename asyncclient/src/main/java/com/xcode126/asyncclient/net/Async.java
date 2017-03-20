package com.xcode126.asyncclient.net;

import android.content.Context;
import android.content.DialogInterface;

import luki.x.XParser;
import luki.x.XTask;
import luki.x.base.AsyncTask;
import luki.x.task.AsyncResult;
import luki.x.task.TaskCallBack;
import luki.x.task.TaskParams;
import luki.x.task.TaskStatusListener;

/**
 * Created by sky on 2017/3/9.
 * 通用的异步任务 范例
 */

public class Async<T> implements TaskStatusListener {

    /**
     * 通用的异步任务
     */
    private Context mContext;
    private boolean isTasking;
    protected String TAG;
    private boolean isShowCover;
    private XTask<ExecResult<T>> mCurrentTask;
    private String coverString;
    private TaskBack<T> defaultCallBack = new TaskBack<T>() {
        @Override
        public void onSuccess(T result) {
        }
        protected void onShowError(String msg) {
        }
    };
    private boolean cancelable;

    public Async(Context context) {
        mContext = context;
        TAG = getClass().getSimpleName();//返回源代码中给出的底层类的简称
    }

    /**
     * @param context     context
     * @param isShowCover 是否显示遮盖层（加载层）
     */
    public Async(Context context, boolean isShowCover) {
        this(context, isShowCover, false);
    }

    /**
     * @param context     context
     * @param isShowCover 是否显示遮盖层（加载层）
     */
    public Async(Context context, boolean isShowCover, boolean cancelable) {
        this(context);
        this.isShowCover = isShowCover;
        this.cancelable = cancelable;
    }

    /**
     * @param context     context
     * @param coverString 遮盖层文字
     */
    public Async(Context context, String coverString) {
        this(context, coverString, false);
    }

    /**
     * @param context     context
     * @param coverString 遮盖层文字
     */
    public Async(Context context, String coverString, boolean cancelable) {
        this(context, true, cancelable);
        this.coverString = coverString;
    }

    /**
     * 自己组合params请求
     *
     * @param params 请求参数
     */
    public void task(TaskParams<ExecResult<T>> params) {
        if (!isTasking()) {
            if (isShowCover) {
                DialogUtils.XDialog dialog = DialogUtils.showLoadingDialog(mContext, coverString, cancelable);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        cancel();
                    }
                });
            }
            isTasking = true;
            mCurrentTask = XParser.INSTANCE.getXTask(this);
            mCurrentTask.execute(params);
        }
        //else
        //L.d(TAG, "tasking " + params.generateKey());
    }

    public void cancel() {
        if (mCurrentTask != null && !mCurrentTask.isCancelled() && mCurrentTask.getStatus() == AsyncTask.Status.RUNNING) {
            mCurrentTask.cancel(true);
        }
    }

    public synchronized boolean isTasking() {
        return isTasking;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public void onEnd() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onCancel() {

    }

    /**
     * 封装的ITaskBack
     *
     * @param <T>
     * @author Luki
     */
    public interface ITaskBack<T> extends TaskCallBack<AsyncResult<ExecResult<T>>> {

        void onSuccess(T result);

        void onFailed(int code, String msg);
    }

    /**
     * 封装的TaskBack
     *
     * @param <T>
     * @author Luki
     */
    public static abstract class TaskBack<T> implements ITaskBack<T> {

    }

}
