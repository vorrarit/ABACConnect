package com.zicure.abacconnect.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zicure.abacconnect.services.PushNotificationService;

/**
 * Created by mid on 11/3/2015.
 */
public class BootCompleteReceiver extends BroadcastReceiver {

	private final String TAG = BootCompleteReceiver.class.getSimpleName();

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "Received boot event");
		Intent srvIntent = new Intent(context, PushNotificationService.class);
		context.startService(srvIntent);

	}
}
