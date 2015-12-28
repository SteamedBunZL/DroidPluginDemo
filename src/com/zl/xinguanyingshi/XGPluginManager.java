package com.zl.xinguanyingshi;

import com.morgoo.droidplugin.pm.PluginManager;
import com.morgoo.helper.compat.PackageManagerCompat;

import android.content.ServiceConnection;
import android.os.RemoteException;
import android.util.Log;
/**
 * ������Y��������
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
	 * �Д����Ƿ��ѽ����b
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
	 * �Д����Ƿ��ʼ�����
	 * @return
	 */
	public boolean judgePluginInitialized(){
		return PluginManager.getInstance().isConnected();
	}

	/**
	 * ���b������@�Y��ԓ�����Y���
	 * @param apkfile
	 * @return ͨ�^intֵ���Д������b�Ƿ�ɹ�
	 * @throws RemoteException
	 */
	public int installPlugin(String apkfile) throws RemoteException{
		return PluginManager.getInstance().installPackage(apkfile, 0);
	}
	
	/**
	 * ���²�����@�Y��ԓ�������
	 * @param apkfile
	 * @return
	 * @throws RemoteException
	 */
	public int updatePlugin(String apkfile) throws RemoteException{
		return PluginManager.getInstance().installPackage(apkfile, PackageManagerCompat.INSTALL_REPLACE_EXISTING);
	}
	
	/**
	 * �Д����Ƿ��bʧ�� ͨ�^{@link #installPlugin(String)}����ֵ�Д�
	 * @param re true ���bʧ��
	 * @return
	 */
	public boolean judgePluginInstallFailed(int re){
		return re == PluginManager.INSTALL_FAILED_NO_REQUESTEDPERMISSION;
	}
	
	/**
	 * ж�d���
	 * @param packageName
	 * @throws RemoteException
	 */
	public void deletePlugin(String packageName) throws RemoteException{
		PluginManager.getInstance().deletePackage(packageName,0);
	}
	
	/**
	 * ��plugin���뵽service��
	 * @param sc
	 */
	public void addPluginToService(ServiceConnection sc){
		PluginManager.getInstance().addServiceConnection(sc);
	}
	
	/**
	 * ��plugin�Ƴ�service����
	 * @param sc
	 */
	public void romvePluginService(ServiceConnection sc){
		 PluginManager.getInstance().removeServiceConnection(sc);
	}
}
