package com.example.ormliteandroidsqlcipher.notifyservice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.ormliteandroidsqlcipher.R;

/**
 * Activity which starts the service when it called.
 * 
 * @author kevingalligan
 */
public class MyActivity extends Activity {

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_notifyservice);

		findViewById(R.id.startService).setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				GetStuffService.setAlarm(MyActivity.this, 20);
			}
		});
	}
}
