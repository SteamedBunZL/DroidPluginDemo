package com.zl.xinguanyingshi;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class Utils {
	private static final String TAG = Utils.class.getSimpleName();
	
	public  static PackageManager getXGPackageManager(Context context){
		return context.getPackageManager();
	}
	
	public static String getMainInterfacefile(){
		return "/sdcard/MainInterfacePlugin.apk";
	}
	
	public static  PackageInfo getApkPackageInfo(PackageManager pm,String apkfile){
		PackageInfo info = pm.getPackageArchiveInfo(apkfile,PackageManager.GET_ACTIVITIES);
		return info;
	}
	
	public static String getPluginPackageName(PackageInfo info){
		return info.packageName;
	}
	
	public static void getDataPath(Context context){
		Log.i(TAG, "cachedir :" + context.getCacheDir().getPath());
	}
	
	
}
