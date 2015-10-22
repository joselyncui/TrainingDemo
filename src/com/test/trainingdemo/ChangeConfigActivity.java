package com.test.trainingdemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.test.fragment.ConfigChangeFragment;

public class ChangeConfigActivity extends Activity implements ConfigChangeFragment.ConfigCallbacks{
	private static final String TAG = "config_change_fragment";
	private ConfigChangeFragment mConfigChangeFragment;
	private ProgressBar mProgressBar;
	private TextView mPercent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_config);
		
		System.out.println("config change activity onCreate");
		
		FragmentManager fm = getFragmentManager();
		mConfigChangeFragment = (ConfigChangeFragment) fm.findFragmentByTag(TAG);
		if(mConfigChangeFragment == null) {
			mConfigChangeFragment = new ConfigChangeFragment();
			fm.beginTransaction().add(mConfigChangeFragment, TAG).commit();
		}
		
		mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
		mPercent = (TextView) findViewById(R.id.text_percent);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_config, menu);
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

	@Override
	public void onPreExecute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProgressUpdate(int percent) {
		mProgressBar.setProgress(percent);
		mPercent.setText(percent +"%");
		
	}

	@Override
	public void onCancelled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPostExecute() {
		// TODO Auto-generated method stub
		
	}
}
