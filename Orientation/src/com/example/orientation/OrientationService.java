package com.example.orientation;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

public class OrientationService  extends Service implements SensorEventListener {	
	private SensorManager sensorManager;
	boolean landscape;
	LinearLayout orientationChanger;
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		
		Toast.makeText(this,"Orientation service created", Toast.LENGTH_LONG).show();
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				100);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		sensorManager.unregisterListener(this);
		Toast.makeText(this, "Orientation service deleted", Toast.LENGTH_LONG).show();
	}
	
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;
		// Movement
		float x = values[0];
		float y = values[1];
		if(x>9 && landscape == false){
			landscape = true;
			LinearLayout orientationChanger = new LinearLayout(this);
			WindowManager.LayoutParams orientationLayout = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY, 0, PixelFormat.RGBA_8888);
			WindowManager wm = (WindowManager) this.getSystemService(Service.WINDOW_SERVICE);
			orientationLayout.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
			wm.addView(orientationChanger, orientationLayout);
			orientationChanger.setVisibility(View.VISIBLE);
		}
		if(y>9 && landscape == true){
			landscape = false;
			LinearLayout orientationChanger = new LinearLayout(this);
			WindowManager.LayoutParams orientationLayout = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY, 0, PixelFormat.RGBA_8888);
			WindowManager wm = (WindowManager) this.getSystemService(Service.WINDOW_SERVICE);
			orientationLayout.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
			wm.addView(orientationChanger, orientationLayout);
			orientationChanger.setVisibility(View.VISIBLE);
		}

		
	}

}

