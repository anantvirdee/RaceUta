package com.parse.starter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.anantvirdee.mainbackup.R;
public class LoginActivity extends Activity{
	TextView username, password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}
	public void onClick(View view) throws ParseException{
		switch(view.getId()){
		case R.id.bLogin:
			startLogin(view);
			break;
		case R.id.bSignup:
			startSignup(view);
			break;		
		}
	}
	
	public void startLogin(View view) throws ParseException{
		username = (TextView)findViewById(R.id.txtUsername);
		password = (TextView)findViewById(R.id.txtPassword);
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
		query.whereEqualTo("userName", username.getText().toString());
		query.whereEqualTo("password", password.getText().toString());
		query.getFirstInBackground(new GetCallback<ParseObject>() {
			  public void done(ParseObject object, ParseException e) {
			    if (object == null) {
			      Log.d("score", "The getFirst request failed.");
			    } else {
			      Log.d("score", "Retrieved the object.");
			      Intent intent = new Intent(LoginActivity.this, com.example.mainbackup.RaceMainActivity.class);
			      intent.putExtra("username",username.getText().toString());
			      startActivity(intent);
			    }
			  }
			});	}

	public void startSignup(View view){
		Intent intent = new Intent(this, SignUpActivity.class);
		   startActivity(intent);		
	}

}
