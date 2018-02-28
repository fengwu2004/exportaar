package com.yellfun.www.exportaar;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tencent.smtt.sdk.QbSdk;

public class APPAplication extends Application {

	@Override
	public Context getBaseContext() {
		return super.getBaseContext();
	}

	@Override
	public void onCreate() {

//		Toast.makeText(getApplicationContext(), "今天是个好日子!!!!", Toast.LENGTH_SHORT).show();
		// TODO Auto-generated method stub
		super.onCreate();
		//搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。

		final Context that = getApplicationContext();

		QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
			
			@Override
			public void onViewInitFinished(boolean arg0) {
				// TODO Auto-generated method stub
				//x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
				if (arg0) {

					Toast.makeText(that, "x5内核加载成功", Toast.LENGTH_SHORT).show();
				}
				else {

					Toast.makeText(that, "x5内核加载失败", Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onCoreInitFinished() {

				// TODO Auto-generated method stub

				Toast.makeText(that, "x5内核加载完毕", Toast.LENGTH_SHORT).show();
			}
		};
		//x5内核初始化接口
		QbSdk.initX5Environment(getApplicationContext(),  cb);
	}

}
