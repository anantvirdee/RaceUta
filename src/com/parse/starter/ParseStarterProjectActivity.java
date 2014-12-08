package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.anantvirdee.mainbackup.R;
public class ParseStarterProjectActivity extends Activity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ParseAnalytics.trackAppOpened(getIntent());
	}
	public void onClick(View view){
		switch(view.getId()){
		case R.id.button1:
			Intent intent = new Intent(ParseStarterProjectActivity.this, LoginActivity.class);
			   startActivity(intent);	
			break;
		}
	}
}
