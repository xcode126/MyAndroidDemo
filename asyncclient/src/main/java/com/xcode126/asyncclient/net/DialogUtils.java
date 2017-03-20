package com.xcode126.asyncclient.net;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xcode126.asyncclient.R;
import com.xcode126.asyncclient.util.IConstant;
import com.xcode126.asyncclient.util.StoneApp;


public class DialogUtils {

	private static XDialog mDialog;
	private static XDialog mUpdatingDialog;

	public static AlertDialog createAlertDialog(Context context, int title, CharSequence[] items, DialogInterface.OnClickListener listener) {
		AlertDialog dialog = new AlertDialog.Builder(context).setTitle(title).setItems(items, listener).create();
		dialog.show();
		return dialog;
	}

	public static AlertDialog createSingleAlertDialog(Context context, int title, CharSequence[] items, int checkedItem,
													  DialogInterface.OnClickListener listener) {
		AlertDialog dialog = new AlertDialog.Builder(context).setTitle(title).setSingleChoiceItems(items, checkedItem, listener).create();
		dialog.show();
		return dialog;
	}

	public static XDialog showLoadingDialog(Context context, String msg, boolean cancelable) {
		dismiss();
		mDialog = new XDialog(context, R.style.Dialog);
		mDialog.setCancelable(cancelable);
		mDialog.setCanceledOnTouchOutside(cancelable);
		mDialog.setMessage(msg);
		mDialog.show();
		return mDialog;
	}

	public static void dismiss() {
		try {
			if (mDialog != null && mDialog.isShowing()) {
				mDialog.dismiss();
			}
		} catch (Exception e) {
		}
		mDialog = null;
	}

	public synchronized static void showUpdatingDialog(Context context) {
		if (mUpdatingDialog != null) {
			return;
		}
		final MessageDialog dialog = new MessageDialog(context);
		dialog.setImage(R.mipmap.img_updating);
		dialog.setMessage("石头理财后台正在进行升级，升级期间App不能操作，请稍等片刻，石头立马回来，回来之后更精彩！", Gravity.LEFT);
		dialog.setCancelable(false);
		dialog.setButton1("确定", new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				StoneApp.exit();
				mUpdatingDialog = null;
				dialog.dismiss();
			}
		});
		dialog.show();
		mUpdatingDialog = dialog;
	}

	public static class XDialog extends Dialog implements IConstant {
		private TextView vMessage;

		public XDialog(Context context, int theme) {
			super(context, theme);
			setContentView(R.layout.custom_progressdialog);
		}

		public XDialog(Context context) {
			this(context, R.style.Dialog);
		}

		public void setMessage(CharSequence msg) {
			this.setMessage(msg, Gravity.CENTER);
		}

		public void setMessage(CharSequence msg, int gravity) {
			if (vMessage == null) {
				vMessage = (TextView) findViewById(R.id.dialog_message);
			}
			if (vMessage != null) {
				vMessage.setGravity(gravity);
				if (TextUtils.isEmpty(msg)) {
					vMessage.setVisibility(GONE);
				} else {
					vMessage.setText(msg);
					vMessage.setVisibility(VISIBLE);
				}
			}
		}

	}
}
