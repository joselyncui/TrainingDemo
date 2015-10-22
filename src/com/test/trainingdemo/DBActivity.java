package com.test.trainingdemo;

import com.test.trainingdemo.ReaderContract.ReaderDBHelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DBActivity extends Activity {
	
	private ReaderDBHelper readerDBHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_db);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.db, menu);
		return true;
	}

}
