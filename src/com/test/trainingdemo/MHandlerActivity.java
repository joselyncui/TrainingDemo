package com.test.trainingdemo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import android.R.integer;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MHandlerActivity extends Activity {
	
	private TextView text1;
	private Button btnChangeInThread;
	private Button btnCreateInThread;
	private Button btnStopLooper;
	
	private Handler mHandler;
	private Handler threadHandler;
	private Thread mThread;
	private Executor executor = Executors.newFixedThreadPool(5);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mhandler);
		
		text1 = (TextView) findViewById(R.id.text1);
		
		btnChangeInThread = (Button) findViewById(R.id.btn_change_in_thread);
		btnCreateInThread = (Button) findViewById(R.id.btn_handler_in_thread);
		btnStopLooper = (Button) findViewById(R.id.btn_stop_looper);
		
		btnChangeInThread.setOnClickListener(new MClickListener());
		btnCreateInThread.setOnClickListener(new MClickListener());
		btnStopLooper.setOnClickListener(new MClickListener());
		
		mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				System.out.println("msg " + msg);
				text1.setText(msg.arg1+"");
				super.handleMessage(msg);
			}
			
		};
		
		for (int i = 0; i < 150; i++) {
			
			new MAsyncTask().executeOnExecutor(executor, i);
//			new MAsyncTask().execute(i);
		}
		
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		mThread = new MyThread("first");
		mThread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mhandler, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	class MClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.btn_change_in_thread:
				Message msg = Message.obtain();
				msg.arg1 = 123;
				mHandler.sendMessage(msg);
				break;
			case R.id.btn_handler_in_thread:
				threadHandler.post(new Runnable() {
					
					@Override
					public void run() {
						System.out.println("thread " + Thread.currentThread().getName());
					}
				});
				break;
			case R.id.btn_stop_looper:
				threadHandler.sendEmptyMessage(1);
				break;
			default:
				break;
			}
		}
	}
	
	class MyThread extends Thread{
		public MyThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			Looper.prepare();
			threadHandler = new Handler(){
				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);
					Looper.myLooper().quit();
				}
			};
			Looper.loop();
			System.out.println("stop");
			super.run();
		}
		
		
	}
	
	class MAsyncTask extends AsyncTask<Integer, integer, String>{

		@Override
		protected String doInBackground(Integer... params) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() +"-" + params[0]);
			return null;
		}
		
	}
}
