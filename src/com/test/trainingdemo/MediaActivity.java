package com.test.trainingdemo;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MediaActivity extends Activity {
	
	private Button mBtnHardCtrl;
	private Button mBtnPlayBackCtrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media);
		
		mBtnHardCtrl = (Button) findViewById(R.id.btn_vol_ctrl);
		mBtnPlayBackCtrl = (Button) findViewById(R.id.btn_play_ctrl);
		
		mBtnHardCtrl.setOnClickListener(new MClickListener());
		mBtnPlayBackCtrl.setOnClickListener(new MClickListener());
		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.media, menu);
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
	
	private class MClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_vol_ctrl:
				
				break;
			case R.id.btn_play_ctrl:
				break;
				
			default:
				break;
			}
			
		}
		
	}
}
