package com.test.trainingdemo;

import com.test.utils.ImgUtil;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MNotificationActivity extends Activity {
	private Button btnUpdateNot;
	NotificationManager mNotificationMgr;
	NotificationCompat.Builder mBuilder;
	
	// Sets an ID for the notification
	int mNotificationId = 001;
	int numMessages = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mnotification_activity);
		mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.img)
				.setContentTitle("first notification")
				.setContentText("this is my first notification");

		Intent resultIntent = new Intent(this, ResultActivity.class);
		// Because clicking the notification opens a new ("special") activity,
		// there's
		// no need to create an artificial back stack.
		PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
				resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);

		// Gets an instance of the NotificationManager service
		mNotificationMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// Builds the notification and issues it.
		mNotificationMgr.notify(mNotificationId, mBuilder.build());

		btnUpdateNot = (Button) findViewById(R.id.btn_update_not);
		btnUpdateNot.setOnClickListener(new MClickListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mnotification_activity, menu);
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

	class MClickListener implements View.OnClickListener {

		Intent intent = null;

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_update_not:
				mNotificationMgr = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
				// Sets an ID for the notification, so it can be update
				mBuilder = new NotificationCompat.Builder(MNotificationActivity.this)
							.setContentTitle("new message")
							.setContentText("You receive a new message")
							.setSmallIcon(R.drawable.img_9)
							.setLargeIcon(
									ImgUtil.decodeSampleBitmapFromResource(
											getResources(), R.drawable.img_9, 600, 600));		
				numMessages = 0;
				// Start of a loop that processes data and then notifies the user
				mNotificationMgr.notify(mNotificationId, mBuilder.build());
				break;

			default:
				break;
			}

		}

	}
}
