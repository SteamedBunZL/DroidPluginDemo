package com.zl.xinguanyingshi;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.util.Log;

public class MainInterfaceLoadThread extends Thread{
	
	private static final String TAG = MainInterfaceLoadThread.class.getSimpleName();
	
	private Context mContext;
	
	private XGPluginManager mManager;
	
	private PluginInstalledInterface mInterface;
	
	private volatile boolean bUpdated;
	
	public MainInterfaceLoadThread(Context context,PluginInstalledInterface sInterace,boolean update) {
		this.mContext = context;
		this.mManager = XGPluginManager.getInstance();
		this.mInterface = sInterace;
		this.bUpdated = update;
	}
	
	@Override
	public void run() {
		if (!mManager.judgePluginInitialized()) {
			Log.w(TAG, "����������ڳ�ʼ����Ո������ԇ������");
			return;
		}
		String pgName = getMainInterfacePackageName();
		try {
		if (bUpdated) {
			int result = mManager.updatePlugin(pgName);
			if (mManager.judgePluginInstallFailed(result)) {
				Log.w(TAG, "����ʧ��");
				mInterface.success(pgName);
			}else {
				Log.w(TAG, "���³ɹ�");
				mInterface.fail();
			}
			return;
		}
		if (mManager.judgePluginInstalled(pgName)) {
			Log.w(TAG, "�Ѿ���װ�ˣ������ٰ�װ");
			mInterface.success(pgName);
		}else {
				int result = mManager.installPlugin(Utils.getMainInterfacefile());
				if (mManager.judgePluginInstallFailed(result)) {
					Log.w(TAG, "���bʧ��");
					mInterface.fail();
				}else {
					Log.w(TAG, "���b�ɹ�");
					mInterface.success(pgName);
				}
		}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private String getMainInterfacePackageName(){
		PackageManager manager = Utils.getXGPackageManager(mContext);
		PackageInfo info = Utils.getApkPackageInfo(manager, Utils.getMainInterfacefile());
		String pgName = Utils.getPluginPackageName(info);
		return pgName;
	}
}
