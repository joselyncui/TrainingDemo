package com.test.trainingdemo;

import com.test.custom.MButton;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class MDisEventActivity extends Activity {
	private MButton btnClick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mdis_event);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mdis_event, menu);
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
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			System.out.println("MDisEventActivity dispatch  ACTION_DOWN" );
			break;
		case MotionEvent.ACTION_MOVE:
			System.out.println("MDisEventActivity dispatch ACTION_MOVE" );
			break;
		case MotionEvent.ACTION_UP:
			System.out.println("MDisEventActivity dispatch ACTION_UP" );
			break;
		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			System.out.println("MDisEventActivity onTouchEvent  ACTION_DOWN" );
			break;
		case MotionEvent.ACTION_MOVE:
			System.out.println("MDisEventActivity onTouchEvent ACTION_MOVE" );
			break;
		case MotionEvent.ACTION_UP:
			System.out.println("MDisEventActivity onTouchEvent ACTION_UP" );
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}
}
