package com.test.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MIntentService extends IntentService{
	private final String TAG = "service";
	
	public MIntentService(){
		super("MIntentService");
	}
	
	public MIntentService(String name) {
		super(name);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i(TAG, "onHandleIntent");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}

}
