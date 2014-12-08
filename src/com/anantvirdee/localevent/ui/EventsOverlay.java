package com.anantvirdee.localevent.ui;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import com.anantvirdee.localevent.engine.QueryEngine;

public class EventsOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext = null;
	
	public EventsOverlay(Drawable defaultMarker, Context context) {
		//super(defaultMarker);
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	@Override
	protected OverlayItem createItem(int index) {
		return (OverlayItem)mOverlays.get(index);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	
	public void addItem(OverlayItem item) {
		mOverlays.add(item);
		populate();
	}
	
	@Override
	protected boolean onTap(int index) {
	  //OverlayItem item = mOverlays.get(index);
	  //AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  //dialog.setTitle(item.getTitle());
	  //dialog.setMessage(item.getSnippet());
	  //dialog.show();
	  
		//show Event Detail Activity
	  Intent intent = new Intent();
	  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	  intent.setClass(mContext, EventDetailActivity.class);
	  intent.putExtra(EventDetailActivity.KEY_EVENT_HTML, QueryEngine.getInstance(mContext).getEvents().getEntryAt(index).getHtmlDescription());
	  mContext.startActivity(intent);
	  
	  return true;
	}
}
