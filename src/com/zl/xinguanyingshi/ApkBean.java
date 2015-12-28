package com.zl.xinguanyingshi;

import android.content.pm.PackageInfo;

public class ApkBean {
	
	private  String apkFile;
	
	private  PackageInfo packageInfo;
	
	private boolean installing = false;
	
	public ApkBean(String apkFile, PackageInfo packageInfo) {
		this.apkFile = apkFile;
		this.packageInfo = packageInfo;
	}

	public String getApkFile() {
		return apkFile;
	}

	public void setApkFile(String apkFile) {
		this.apkFile = apkFile;
	}

	public PackageInfo getPackageInfo() {
		return packageInfo;
	}

	public void setPackageInfo(PackageInfo packageInfo) {
		this.packageInfo = packageInfo;
	}

	public boolean isInstalling() {
		return installing;
	}

	public void setInstalling(boolean installing) {
		this.installing = installing;
	}
	
	
	
}
