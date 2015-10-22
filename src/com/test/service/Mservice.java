package com.test.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Mservice extends Service{
	private final String TAG = "service";
	
	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
		super.onCreate();
		
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		Log.i(TAG, "onStart");
		super.onStart(intent, startId);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
