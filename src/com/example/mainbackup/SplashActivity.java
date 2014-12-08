package com.example.mainbackup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.anantvirdee.mainbackup.R;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.parse.starter.LoginActivity;
public class SplashActivity extends Activity {
 
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add your initialization code here
        Parse.initialize(this, "IBZNRrPuvMiFjxWBBXwJSk08aFRTUGq51UH8a5g7", "MIPGVknQRQkhf0c8a565hV3bbYLeIOo7WLDOab5h");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
          
        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);
        
        ParseACL.setDefaultACL(defaultACL, true);
        setContentView(R.layout.activity_splash);
 
        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, com.parse.starter.LoginActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
 
}