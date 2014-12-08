package com.anantvirdee.localevent.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class EventDetailActivity extends Activity {

	private static final String LOG_TAG 		= "EventDetailActivity";
	public static final String KEY_EVENT_HTML 	= "com.anantvirdee.localevent.event.html";
	public static final String KEY_EVENT_TICKET = "ticket";
	public static final String KEY_EVENT_URL 	= "url";
	public static final String KEY_EVENT_REGION = "region";
	public static final String KEY_EVENT_ID		="id";
	
	//private TextView 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "EventDetailActivity onCreate called");
		super.onCreate(savedInstanceState);
		 WebView webview = new WebView(this);
		 setContentView(webview);
		 Bundle extras = getIntent().getExtras();
//		 String html = extras.getString(KEY_EVENT_HTML);
//		 String ticket = extras.getString(KEY_EVENT_TICKET);
//		 String url = extras.getString(KEY_EVENT_URL);
		 String eventid = extras.getString(KEY_EVENT_ID);
		// String main=html+' '+url+ " "+eventid;
		 String url ="http://www.eventbrite.com/tickets-external?eid="+eventid+"&ref=etckt&v=2";
		 webview.getSettings().setJavaScriptEnabled(true);
		 webview.loadUrl(url);
		 webview.setWebViewClient(new WebViewClient());
//         if (url != null) {
//        	 Log.i(LOG_TAG, url);
//        	 webview.loadData(url, "text/html", "utf-8");
//         }
//         if (ticket != null) {
//        	 
//        	 webview.loadData(ticket, "text/html", "utf-8");
//         }
		 
	}

	@Override
	protected void onResume() {
		Log.i(LOG_TAG, "EventDetailActivity onResume called");
		super.onResume();
		
	}

	
	
	
}
