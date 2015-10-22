package com.test.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MBindService extends Service{
	private final String TAG = "service";
	private MBinder mBinder = new MBinder();
	
	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "onBind");
		return mBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.i(TAG, "onUnbind");
		return super.onUnbind(intent);
	}
	
	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}

	public class MBinder extends Binder{
		public MBindService getService(){
			return MBindService.this;
		}
		
	}
}
