package com.example.orientation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MainActivity extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent service = new Intent(context, OrientationService.class);
		context.startService(service); 		
	}
} 