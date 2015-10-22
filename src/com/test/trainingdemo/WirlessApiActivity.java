package com.test.trainingdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class WirlessApiActivity extends Activity {
	
	private Button btnNetDis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wirless_api);
		btnNetDis = (Button) findViewById(R.id.btn_net_disc);
		
		btnNetDis.setOnClickListener(new MClickListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wirless_api, menu);
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
		Intent intent = null;
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_net_disc:
				intent = new Intent(WirlessApiActivity.this, NetDiscActivity.class);
				startActivity(intent);
				break;

			default:
				break;
			}
			
		}
		
	}
}
