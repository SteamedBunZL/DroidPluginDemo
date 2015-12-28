package com.zl.xinguanyingshi;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class SplashActivity extends Activity implements ServiceConnection,PluginInstalledInterface{
	private static final String TAG = SplashActivity.class.getSimpleName();
	
	private MyDatabaseHelper mHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
		Utils.getDataPath(this);
		waitTime();
	}
	/**
	 * 模拟加载过程
	 */
	private void waitTime() {
	
				//TODO 1.加载数据库等资源
					SQLiteDatabase db = mHelper.getWritableDatabase();
					ContentValues values = new ContentValues();
					values.put("name", "Da sha");
					values.put("author", "zhangsan");
					values.put("pages", 454);
					values.put("price", 16.96);
					db.insert("book", null, values);
					//TODO 加载插件主界面
					//1.第一次的话要下载安装
					//2.每次由都由网络请求来判断是否应该更新主界面的插件，这里主界面要用到host的数据库资源(是否可以？)
					if (XGPluginManager.getInstance().judgePluginInitialized()) {
						Mystart();
					}else {
						XGPluginManager.getInstance().addPluginToService(this);
					}
				
	
	}
	
	private void Mystart(){
		MainInterfaceLoadThread thread = new MainInterfaceLoadThread(SplashActivity.this, SplashActivity.this,false);
		thread.start();
	}
	
	
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		Mystart();
	}
	@Override
	public void onServiceDisconnected(ComponentName name) {
		
	}
	
	
	@Override
	public void success(String pgName) {
		Intent serviceIntent = new Intent("com.zl.maininterfaceplugin.pluginservicetest01");
		startService(serviceIntent);
		Intent intent = new Intent("com.zl.maininterfaceplugin.ActivityTest01");
//		Intent intent = new Intent();
//		intent.setClassName("com.zl.maininterfaceplugin", "com.zl.maininterfaceplugin.PluginActivityTest01");
//		  Intent intent = Utils.getXGPackageManager(this).getLaunchIntentForPackage(pgName);
//          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("splashactivity", "splashactivity传递参数");
          startActivity(intent);
//        finish();
	}
	
	@Override
	protected void onDestroy() {
		XGPluginManager.getInstance().romvePluginService(this);
		super.onDestroy();
	}
	@Override
	public void fail() {
		
	}

	
}
