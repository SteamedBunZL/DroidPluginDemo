package com.zl.xinguanyingshi;

import com.morgoo.droidplugin.pm.PluginManager;
import com.morgoo.helper.compat.PackageManagerCompat;

import android.content.ServiceConnection;
import android.os.RemoteException;
import android.util.Log;
/**
 * 插件管裏器，單例
 * @author ZL
 *
 */
public class XGPluginManager {
	
	private static final String TAG = XGPluginManager.class.getSimpleName();
	
	private static final String path = "/sdcard/";
	
	private XGPluginManager(){}
	
	public static XGPluginManager mManager = new XGPluginManager();
	
	public static XGPluginManager getInstance(){
		return mManager;
	}
	
	/**
	 * 判斷插件是否已經安裝
	 * @param packageName
	 * @return
	 */
	public boolean judgePluginInstalled(String packageName){
		try {
			if (PluginManager.getInstance().getPackageInfo(packageName, 0) != null) {
				return true;
			}
		} catch (RemoteException e) {
			Log.e(TAG, "judgePlugin method",e);
		}
		return false;
	}
	
	/**
	 * 判斷插件是否初始化完成
	 * @return
	 */
	public boolean judgePluginInitialized(){
		return PluginManager.getInstance().isConnected();
	}

	/**
	 * 安裝插件，這裏應該異步裏完成
	 * @param apkfile
	 * @return 通過int值可判斷插件安裝是否成功
	 * @throws RemoteException
	 */
	public int installPlugin(String apkfile) throws RemoteException{
		return PluginManager.getInstance().installPackage(apkfile, 0);
	}
	
	/**
	 * 更新插件，這裏應該異步完成
	 * @param apkfile
	 * @return
	 * @throws RemoteException
	 */
	public int updatePlugin(String apkfile) throws RemoteException{
		return PluginManager.getInstance().installPackage(apkfile, PackageManagerCompat.INSTALL_REPLACE_EXISTING);
	}
	
	/**
	 * 判斷插件是否安裝失敗 通過{@link #installPlugin(String)}返回值判斷
	 * @param re true 安裝失敗
	 * @return
	 */
	public boolean judgePluginInstallFailed(int re){
		return re == PluginManager.INSTALL_FAILED_NO_REQUESTEDPERMISSION;
	}
	
	/**
	 * 卸載插件
	 * @param packageName
	 * @throws RemoteException
	 */
	public void deletePlugin(String packageName) throws RemoteException{
		PluginManager.getInstance().deletePackage(packageName,0);
	}
	
	/**
	 * 把plugin加入到service中
	 * @param sc
	 */
	public void addPluginToService(ServiceConnection sc){
		PluginManager.getInstance().addServiceConnection(sc);
	}
	
	/**
	 * 把plugin移除service綁定
	 * @param sc
	 */
	public void romvePluginService(ServiceConnection sc){
		 PluginManager.getInstance().removeServiceConnection(sc);
	}
}
