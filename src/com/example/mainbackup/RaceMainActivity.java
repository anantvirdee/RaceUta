package com.example.mainbackup;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.anantvirdee.mainbackup.R;

public class RaceMainActivity extends Activity{

	public int currentImageIndex = 0;
	ImageView slidingImage;
	Button bNews,bEvents;

	private int[] IMAGE_IDS = {
			R.drawable.fsaeimageone, R.drawable.fsaeimgtwo, R.drawable.fsaeimgthree,
			R.drawable.fsaeimgfive,R.drawable.fsaeimgsix
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_race_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		bNews =(Button) findViewById(R.id.bNews);
		bEvents =(Button) findViewById(R.id.bEvents);
		final Handler mHandler = new Handler();
		// Create runnable for posting
		final Runnable mUpdateResults = new Runnable() {
			public void run() {
				AnimateandSlideShow();
			}
		};      
		int delay = 1000; // delay for 1 sec.
		int period = 15000; // repeat every 4 sec.
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				mHandler.post(mUpdateResults);
			}
		}, delay, period);   
	}

	public void onClick(View view){
		switch(view.getId()){
		case R.id.bNews:
			openNewspage(view);
			break;
		case R.id.bEvents:
			openEventpage(view);
			break;
		case R.id.bFanWall:
			openFanwall(view);
			break;
		case R.id.bGallery:
			openGallery(view);
			break;
		}
	}

	private void AnimateandSlideShow() {        
		slidingImage = (ImageView)findViewById(R.id.UtaLogo);
		slidingImage.setImageResource(IMAGE_IDS[currentImageIndex%IMAGE_IDS.length]);
		currentImageIndex++;
		Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		slidingImage.startAnimation(rotateimage);             	        
	}    

	public void openGallery(View view) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, GalleryActivity.class);
		startActivity(intent);		  
	}

	public void openFanwall(View view) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, com.parse.starter.FanWallActivity.class);
		startActivity(intent);		  
	}

	/** Called when the user clicks the Event button */
	public void openEventpage(View view) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(RaceMainActivity.this, com.anantvirdee.localevent.ui.EventsListActivity.class);
		startActivity(intent);		  
	}

	/** Called when the user clicks the News button */
	public void openNewspage(View view) {
		Intent intent = new Intent(this, uta.se1.rss.reader.MainRSSActivity.class);
		startActivity(intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.race_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
