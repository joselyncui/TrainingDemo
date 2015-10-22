package com.test.trainingdemo;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CheckMemoryActivity extends Activity {
	private final String TAG = "CheckMemoryActivity";
	
	private Button btnGetMemory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_memory);
		btnGetMemory = (Button) findViewById(R.id.btn_get_memory);
		
		btnGetMemory.setOnClickListener(new BtnClickListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_memory, menu);
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
	
	class BtnClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_get_memory:
				displayBriefMemory();
				break;

			default:
				break;
			}
			
		}
		
	}
	
	private void displayBriefMemory(){
		final ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
		activityManager.getMemoryInfo(info);
		
		Log.i(TAG, "系统剩余内存 " + (info.availMem>>10) +"k");
		Log.i(TAG, "系统是否处于低内存运行 " + info.lowMemory);
		Log.i(TAG, "当系统摄于内存低于 " + (info.threshold>>10) +" 就看成是低内存运行");
		
	} 
}
