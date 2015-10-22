package com.test.trainingdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LifeCircleActivity extends Activity {
	private static final String TAG = "lifecircle";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_life_circle);
		
		Log.i(TAG, "onCreate");
	}

	@Override
	protected void onResume() {
		Log.i(TAG, "onResume");
		super.onResume();
	}
	@Override
	protected void onStart() {
		Log.i(TAG, "onStart");
		super.onStart();
	}
	@Override
	protected void onRestart() {
		Log.i(TAG, "onRestart");
		super.onRestart();
	}
	@Override
	protected void onPause() {
		Log.i(TAG, "onPause");
		super.onPause();
	}
	@Override
	protected void onDestroy() {
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}
	
	@Override
	protected void onStop() {
		Log.i(TAG, "onStop");
		super.onStop();
	}
	
	public void open2(View v) {
		Intent intent = new Intent(LifeCircleActivity.this, LifeCircle2Activity.class);
		startActivity(intent);
	}
}
