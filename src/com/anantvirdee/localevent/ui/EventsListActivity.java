package com.anantvirdee.localevent.ui;

import com.anantvirdee.mainbackup.R;
import com.anantvirdee.localevent.datamodel.EventCollection;
import com.anantvirdee.localevent.engine.IQueryEngineListener;
import com.anantvirdee.localevent.engine.QueryEngine;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class EventsListActivity extends Activity implements OnItemClickListener, IQueryEngineListener {
	
	private ListView mEventListView = null;
	private TextView mTitle = null;
	private EventsListAdapter mAdaptor = null;
	private String mLastErrorMessage = null;
	private QueryEngine mEngine = null;
	
	public static final int DIALOG_ERROR_ID = 0;
	private static final String LOG_TAG = "EventsListActivity";
	public static final int MENU_ITEM_MAP = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        mEventListView = (ListView) findViewById(android.R.id.list);
        mEventListView.setOnItemClickListener(this);
        mTitle = (TextView) findViewById(R.id.title);
        mTitle.setText(R.string.events_list_title);
        
        mEngine = QueryEngine.getInstance(this.getApplicationContext());
        mEngine.searchNearbyLocalEvents(this);
    }

	@Override
	protected void onResume() {
		super.onResume();
	}
    
	@Override
	protected void onPause() {
		super.onPause();
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//show Event Detail Activity
		Intent intent = new Intent();
		intent.setClass(this, EventDetailActivity.class);
		
		intent.putExtra(EventDetailActivity.KEY_EVENT_HTML, mEngine.getEvents().getEntryAt(position).getHtmlDescription());
		intent.putExtra(EventDetailActivity.KEY_EVENT_TICKET, mEngine.getEvents().getEntryAt(position).getMticketDetails());
		intent.putExtra(EventDetailActivity.KEY_EVENT_URL, mEngine.getEvents().getEntryAt(position).getUrl());
		intent.putExtra(EventDetailActivity.KEY_EVENT_REGION, mEngine.getEvents().getEntryAt(position).getmRegion());
		intent.putExtra(EventDetailActivity.KEY_EVENT_ID, mEngine.getEvents().getEntryAt(position).getmId());
		
		
		startActivity(intent);
	}

	public void notifyProcessingSuccess(EventCollection collection) {
		Log.i(LOG_TAG, "notifyProcessingSuccess");
		
		mAdaptor = new EventsListAdapter(this, collection);
		mEventListView.setAdapter(mAdaptor);
	}

	
	protected Dialog onCreateDialog(int id) {
	    Dialog dialog;
	    switch(id) {
	    case DIALOG_ERROR_ID:
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setMessage(mLastErrorMessage)
	    	       .setCancelable(false)
	    	       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int id) {
	    	                EventsListActivity.this.finish();
	    	           }
	    	       })
	    	       ;
	    	dialog = builder.create();
	    	return dialog;
	    	
	    }
	    return null;
	}
	
	public void notifyProcessingFailure(EngineError error) {
		switch (error) {
			case IO_ERR:
				mLastErrorMessage = this.getText(R.string.error_io).toString();
				break;
			case URL_ERR:
				mLastErrorMessage = this.getText(R.string.error_url).toString();
				break;
			case PARSING_ERR:
				mLastErrorMessage = this.getText(R.string.error_parse).toString();
				break;
			case LOCATION_ERR:
				mLastErrorMessage = this.getText(R.string.error_location).toString();
				break;
			case UNKNOWN_ERR:
			default:
				mLastErrorMessage = this.getText(R.string.error_unknown).toString();
				break;
		}
		showDialog(DIALOG_ERROR_ID);
		
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    super.onCreateOptionsMenu(menu);
    
	    menu.add(0, MENU_ITEM_MAP, 1, "Map It");
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
	    switch (item.getItemId()) {
	    case MENU_ITEM_MAP:
			Intent i = new Intent();
			i.setClass(this, EventsMapActivity.class);
			startActivity(i);
	    	return true;
	    }
	    return false;
	}
	
}