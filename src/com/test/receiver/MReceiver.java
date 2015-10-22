package com.test.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("m receiver ");
		System.out.println(intent.getAction());
		Bundle bundle = new Bundle();
		bundle.putString("msg", "MReceiver");
		setResultExtras(bundle);
		abortBroadcast();
	}

}
