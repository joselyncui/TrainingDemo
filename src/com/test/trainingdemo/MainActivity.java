package com.test.trainingdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * @author MGC01
 *
 */
/**
 * @author MGC01
 *
 */
public class MainActivity extends Activity {
	private Button actionBarBtn;
	private Button dbActivityBtn;
	private Button intentBtn;
	private Button weightBtn;
	private Button fragmentBtn;
	private Button fileBtn;
	private Button shareBtn;
	private Button mediaBtn;
	private Button bitmapBtn;
	private Button configBtn;
	private Button openglBtn;
	private Button animateBtn;
	private Button wirlessBtn;
	private Button notificationBtn;
	private Button memoryBtn;
	private Button handlerBtn;
	private Button dispatchBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		actionBarBtn = (Button) findViewById(R.id.actionBarBtn);
		dbActivityBtn = (Button) findViewById(R.id.dbActivityBtn);
		intentBtn = (Button) findViewById(R.id.intentBtn);
		weightBtn = (Button) findViewById(R.id.weightBtn);
		fragmentBtn = (Button) findViewById(R.id.fragmentBtn);
		fileBtn = (Button) findViewById(R.id.fileBtn);
		shareBtn = (Button) findViewById(R.id.shareBtn);
		mediaBtn = (Button) findViewById(R.id.mediaBtn);
		bitmapBtn = (Button) findViewById(R.id.bitmapBtn);
		configBtn = (Button) findViewById(R.id.changeConfigBtn);
		openglBtn = (Button) findViewById(R.id.openglEsBtn);
		animateBtn = (Button) findViewById(R.id.animationsBtn);
		wirlessBtn = (Button) findViewById(R.id.btn_wirless_api);
		notificationBtn = (Button) findViewById(R.id.btn_notification);
		memoryBtn = (Button) findViewById(R.id.btn_memory);
		handlerBtn = (Button) findViewById(R.id.btn_handler);
		dispatchBtn = (Button) findViewById(R.id.btn_event_disp);
		
		actionBarBtn.setOnClickListener(new BtnClickListener());
		dbActivityBtn.setOnClickListener(new BtnClickListener());
		intentBtn.setOnClickListener(new BtnClickListener());
		weightBtn.setOnClickListener(new BtnClickListener());
		fragmentBtn.setOnClickListener(new BtnClickListener());
		fileBtn.setOnClickListener(new BtnClickListener());
		shareBtn.setOnClickListener(new BtnClickListener());
		mediaBtn.setOnClickListener(new BtnClickListener());
		bitmapBtn.setOnClickListener(new BtnClickListener());
		configBtn.setOnClickListener(new BtnClickListener());
		openglBtn.setOnClickListener(new BtnClickListener());
		animateBtn.setOnClickListener(new BtnClickListener());
		wirlessBtn.setOnClickListener(new BtnClickListener());
		notificationBtn.setOnClickListener(new BtnClickListener());
		memoryBtn.setOnClickListener(new BtnClickListener());
		handlerBtn.setOnClickListener(new BtnClickListener());
		dispatchBtn.setOnClickListener(new BtnClickListener());
	}

	
	
	@Override
	protected void onPause() {
		System.out.println("main pause");
		
		super.onPause();
	}

	
	


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		System.out.println("main save instance state");
		super.onSaveInstanceState(outState);
	}



	@Override
	protected void onStop() {
		System.out.println("main stop");
		super.onStop();
	} 

	

	@Override
	protected void onStart() {
		System.out.println("main start");
		super.onStart();
	}



	@Override
	protected void onRestart() {
		System.out.println("main restart");
		super.onRestart();
	}



	@Override
	protected void onResume() {
		System.out.println("main resume");
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		System.out.println("main destory");
		super.onDestroy();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class BtnClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.actionBarBtn:
				intent = new Intent(getApplicationContext(), ActionBarTest.class);
				startActivity(intent);
				//finish();
				break;
			case R.id.dbActivityBtn:
				intent = new Intent(getApplicationContext(), DBActivity.class);
				startActivity(intent);
				break;
			case R.id.intentBtn:
				intent = new Intent(getApplicationContext(), IntentActivity.class);
				startActivity(intent);
				break;
			case R.id.weightBtn:
				intent = new Intent(MainActivity.this, WeightActivity.class);
				startActivity(intent);
				break;
			case R.id.fragmentBtn:
				intent = new Intent(MainActivity.this, MFragmentActivity.class);
				startActivity(intent);
				break;
			case R.id.fileBtn:
				intent = new Intent(MainActivity.this, FileActivity.class);
				startActivity(intent);
				break;
			case R.id.shareBtn:
				intent = new Intent(MainActivity.this,ShareActivity.class);
				startActivity(intent);
				break;
			case R.id.mediaBtn:
				intent = new Intent(MainActivity.this, MediaActivity.class);
				startActivity(intent);
				break;
			case R.id.bitmapBtn:
				intent = new Intent(MainActivity.this, BitmapActivity.class);
				startActivity(intent);
				break;
			case R.id.changeConfigBtn:
				intent = new Intent(MainActivity.this, ChangeConfigActivity.class);
				startActivity(intent);
				break;
			case R.id.openglEsBtn:
				intent = new Intent(MainActivity.this, OpenGLActivity.class);
				startActivity(intent);
				break;
			case R.id.animationsBtn:
				intent = new Intent(MainActivity.this, AnimateActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_wirless_api:
				intent = new Intent(MainActivity.this, WirlessApiActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_notification:
				intent = new Intent(MainActivity.this, MNotificationActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_memory:
				intent = new Intent(MainActivity.this, CheckMemoryActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_handler:
				intent = new Intent(MainActivity.this, MHandlerActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_event_disp:
				intent = new Intent(MainActivity.this, MDisEventActivity.class);
				startActivity(intent);
				break;
			}
			
		}
	}
	
	
}
