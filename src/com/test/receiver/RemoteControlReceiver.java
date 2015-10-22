package com.test.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

public class RemoteControlReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		if(Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
			KeyEvent event = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
			if(KeyEvent.KEYCODE_MEDIA_PLAY == event.getKeyCode()) {
				System.out.println(" media play ");
			} else if(KeyEvent.KEYCODE_MEDIA_PAUSE == event.getKeyCode()) {
				System.out.println("media pause");
			} 
		}
		
	}

}
