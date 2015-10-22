package com.test.trainingdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MReceiverActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mreceiver);
		
		Intent batteryIntent = getApplicationContext().registerReceiver(null,  
		        new IntentFilter(Intent.ACTION_BATTERY_CHANGED));  
		int currLevel = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);  
		int total = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);  
		int percent = currLevel * 100 / total;  
		Log.i("battery", "battery: " + percent + "%");  
		
		
		
	}
	
	public void send(View v) {
		Intent intent = new Intent();
		intent.setAction("com.test.action.CUSTOM_ACTION");
		sendOrderedBroadcast(intent, "com.test.permission.CUSTOM_PERMISSION");
	}

}
