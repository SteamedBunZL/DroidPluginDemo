package com.zl.xinguanyingshi;

import com.morgoo.droidplugin.PluginHelper;

import android.app.Application;
import android.content.Context;

public class XGApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		PluginHelper.getInstance().applicationOnCreate(getBaseContext());
	}

	@Override
	protected void attachBaseContext(Context base) {
		PluginHelper.getInstance().applicationAttachBaseContext(base);
		super.attachBaseContext(base);
	}
}
