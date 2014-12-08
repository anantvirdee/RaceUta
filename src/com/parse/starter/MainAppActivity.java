package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.anantvirdee.mainbackup.R;
public class MainAppActivity extends Activity{
//	public static String username=null;
	
	Intent inn =getIntent();
	Bundle b = inn.getExtras();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainapp);		
		if(b!=null){
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_LONG;
		CharSequence text = b.getCharSequence("username");
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		}
	}
	
	public void onClick(View view){
		switch(view.getId()){
		case R.id.bFanwall:
			openFanWall();
			break;
		
		}
	}
	
	public void openFanWall(){
		if(b!=null){
		CharSequence username = b.getCharSequence("username");
		Intent intent = new Intent(MainAppActivity.this,FanWallActivity.class);
		intent.putExtra("username",username.toString());
		startActivity(intent);
		}
	}
	
}
