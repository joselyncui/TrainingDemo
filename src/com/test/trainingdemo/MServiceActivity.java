package com.test.trainingdemo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.test.service.MBindService;
import com.test.service.MIntentService;
import com.test.service.Mservice;
import com.test.service.MBindService.MBinder;

public class MServiceActivity extends Activity {
	private AlarmManager manager;
	private PendingIntent pend;
	private MBindService mBindService;
	private MBinder mBinder;
	
	private ServiceConnection connection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			System.out.println("service dis connect");
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("service connect");
			mBinder = (MBinder) service;
			mBindService = mBinder.getService();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mservice);
		
		manager = (AlarmManager)getSystemService(Service.ALARM_SERVICE);
		Intent intent = new Intent(this, Mservice.class);
		pend = PendingIntent.getService(this, 0, intent, 0);
		
	}

	public void startService(View v) {
//		manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 2*1000, pend);
		Intent intent = new Intent(this, MBindService.class);
		startService(intent);
	}
	
	public void bindService(View v) {
		Intent intent = new Intent(this, MBindService.class);
		this.bindService(intent, connection, Context.BIND_AUTO_CREATE);
	}
	
	public void unbindService(View v) {
		unbindService(connection);
	}
	
	public void intentService(View v) {
		Intent intent = new Intent(this, MIntentService.class);
		startService(intent);
	}
	
	public void stopService(View v) {
		mBindService.stopSelf();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(connection);
	}
}
