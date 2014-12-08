package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.anantvirdee.mainbackup.R;
public class SignUpActivity extends Activity{
	TextView username,password,repassword,email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
	
	}
	public void onClick(View view){
		switch (view.getId()){
		case R.id.bCreateuser:
			createNewUser(view);
			break;
		}		
	}
	
	public void createNewUser(View view){
		
		Context context = getApplicationContext();
		int duration = Toast.LENGTH_SHORT;
		username=(TextView) findViewById(R.id.sutxtuserName);
		password=(TextView) findViewById(R.id.sutxtpassword);
		repassword=(TextView) findViewById(R.id.sutxtRepassword);
		email = (TextView) findViewById(R.id.sutxtemail);
		if(password.getText().toString().equals(repassword.getText().toString())){
			ParseObject userObject = new ParseObject("Users");
			userObject.put("userName", username.getText().toString());
			userObject.put("password", password.getText().toString());
			userObject.put("email", email.getText().toString());
			
			userObject.saveInBackground();
			CharSequence text = "User Created";
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
		else{
			CharSequence text = "Password mismatch";
			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}

}
