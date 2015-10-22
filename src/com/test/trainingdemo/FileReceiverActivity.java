package com.test.trainingdemo;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

public class FileReceiverActivity extends Activity {
	private File mParentPath;
	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_receiver);
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		handleViewIntent();
		super.onNewIntent(intent);
	}
	
	private void handleViewIntent(){
		mIntent = getIntent();
		String action = mIntent.getAction();
		if (TextUtils.equals(action, Intent.ACTION_VIEW)) {
			Uri beamUri = mIntent.getData();
			if (TextUtils.equals(beamUri.getScheme(), "file")) {
				mParentPath = handleFileUri(beamUri);
			} else if(TextUtils.equals(beamUri.getScheme(), "content")){
				mParentPath = handleContentUri(beamUri);
			}
		}
	}
	
	private File handleFileUri(Uri beamUri) {
		String fileName = beamUri.getPath();
		File copiedFile = new File(fileName);
		return new File(copiedFile.getParent());
	}
	
	private File handleContentUri(Uri beamUri) {
		int fileNameIndex;
		File copiedFile;
		String fileName;
		if(!TextUtils.equals(beamUri.getAuthority(), MediaStore.AUTHORITY)) {
			
		} else {
			String[] projection = {MediaStore.MediaColumns.DATA};
			Cursor pathCursor = getContentResolver().query(beamUri, projection, null, null, null);
			if(pathCursor!= null && pathCursor.moveToFirst()) {
				fileNameIndex = pathCursor.getColumnIndex(MediaStore.MediaColumns.DATA);
				fileName = pathCursor.getString(fileNameIndex);
				copiedFile = new File(fileName);
				return new File(copiedFile.getParent());
			} 
			
		}
		
		return null;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.file_receiver, menu);
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
}
