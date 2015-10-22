package com.test.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MSecondReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		String msg = getResultExtras(true).getString("msg");
		System.out.println("secomd receiver " + msg);
		
	}

}
