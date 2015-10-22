package com.test.trainingdemo;

import java.util.List;

import org.apache.http.protocol.HTTP;

import com.test.trainingdemo.MainActivity.BtnClickListener;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class IntentActivity extends Activity {
	
	private final String TAG ="IntentActivity";
	private static final int PICK_CONTACT_REQUEST = 1;  // The request code
	private Button telBtn;
	private Button mapBtn;
	private Button webviewBtn;
	private Button emailBtn;
	private Button calendarBtn;
	private Button contactBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_layout);
		
		telBtn = (Button) findViewById(R.id.telBtn);
		mapBtn = (Button) findViewById(R.id.mapBtn);
		webviewBtn = (Button) findViewById(R.id.wenpageBtn);
		emailBtn = (Button) findViewById(R.id.emailBtn);
		calendarBtn = (Button) findViewById(R.id.calendarBtn);
		contactBtn = (Button) findViewById(R.id.contactBtn);
		
		telBtn.setOnClickListener(new BtnClickListener());
		mapBtn.setOnClickListener(new BtnClickListener());
		webviewBtn.setOnClickListener(new BtnClickListener());
		emailBtn.setOnClickListener(new BtnClickListener());
		calendarBtn.setOnClickListener(new BtnClickListener());
		contactBtn.setOnClickListener(new BtnClickListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.intent, menu);
		return true;
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to
	    if (requestCode == PICK_CONTACT_REQUEST) {
	        // Make sure the request was successful
	        if (resultCode == RESULT_OK) {
	            // The user picked a contact.
	            // The Intent's data Uri identifies which contact was selected.
	        	Uri contactUri = data.getData();
	        	String[] projection = {Phone.NUMBER};
	        	Cursor cursor= getContentResolver().query(contactUri, projection, null, null, null);
	        	cursor.moveToFirst();
	        	int column = cursor.getColumnIndex(Phone.NUMBER);
	        	String number = cursor.getString(column);
	        	Log.i(TAG, "number: " + number);
	        	Log.i(TAG, data.getDataString());
	            // Do something with the contact here (bigger example below)
	        }
	    }
		super.onActivityResult(requestCode, resultCode, data);
	}


	class BtnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.telBtn:
				Uri number = Uri.parse("tel:5556");
				Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
				startActivity(callIntent);
				break;
			case R.id.mapBtn:
//				Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
				Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
				Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
				
				PackageManager packageManager = getPackageManager();
				List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
				boolean isIntentSafe = activities.size() > 0;
				if (isIntentSafe) {
					startActivity(mapIntent);
				} else {
					Log.i(TAG, "isIntentSafe "+ isIntentSafe);
				}
				
				
				break;
			case R.id.wenpageBtn:
				Uri webPage = Uri.parse("http://www.baidu.com");
				Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
				startActivity(webIntent);
				break;
			case R.id.emailBtn:
				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
				emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"cy937956803@163.com"});
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Test email");
				emailIntent.putExtra(Intent.EXTRA_TEXT, "email body");
				emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
				
				String title = "Chooser";
				Intent chooser = Intent.createChooser(emailIntent, title);
				if (emailIntent.resolveActivity(getPackageManager()) != null) {
					startActivity(emailIntent);
				}
				
				break;
			case R.id.calendarBtn:
//				Intent calendarIntent = new Intent(Intent.ACTION_INSERT, Events.CONTENT_URI);
//				Calendar beginTime = Calendar.getInstance().set(2012, 0, 19, 7, 30);
//				Calendar endTime = Calendar.getInstance().set(2012, 0, 19, 10, 30);
//				calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
//				calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
//				calendarIntent.putExtra(Events.TITLE, "Ninja class");
//				calendarIntent.putExtra(Events.EVENT_LOCATION, "Secret dojo");
				break;
			case R.id.contactBtn:
				Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
			    pickContactIntent.setType(Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
			    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
				break;
			default:
				break;
			}
			
		}
		
	}

}
