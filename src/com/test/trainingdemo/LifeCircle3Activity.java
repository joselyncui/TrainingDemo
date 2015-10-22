package com.test.trainingdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

public class LifeCircle3Activity extends Activity{
	private static final String TAG = "lifecircle 3" ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_life_circle3);
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
	
	public void open1(View view) {
		Intent intent =new Intent(LifeCircle3Activity.this, LifeCircleActivity.class);
		startActivity(intent);
	}
	
	public void open2(View view) {
		Intent intent =new Intent(LifeCircle3Activity.this, LifeCircle2Activity.class);
		startActivity(intent);
	}
	
	public void open3(View view) {
		Intent intent =new Intent(LifeCircle3Activity.this, LifeCircle3Activity.class);
		startActivity(intent);
	}
}
