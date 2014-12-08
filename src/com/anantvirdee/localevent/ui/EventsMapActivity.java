package com.anantvirdee.localevent.ui;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.anantvirdee.mainbackup.R;
import com.anantvirdee.localevent.datamodel.Event;
import com.anantvirdee.localevent.datamodel.EventCollection;
import com.anantvirdee.localevent.engine.QueryEngine;
import com.anantvirdee.localevent.geo.GeoLocationManager;
import com.anantvirdee.localevent.geo.NoLocationProviderException;

public class EventsMapActivity extends MapActivity  {

		private MapView mMapView;
		private List<Overlay> mapOverlays;
		private Drawable drawable;
		private EventsOverlay itemizedOverlay;
		private MapController mMapController;
		private QueryEngine mEngine = null;
		private static final String LOG_TAG = "EventsMapActivity";
		
	/** Called when the activity is first created. */
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.event_map);
	        
	        mMapView = (MapView) findViewById(R.id.mapview);
	        mMapView.setBuiltInZoomControls(true);

	        mMapController = mMapView.getController();
	        
			GeoLocationManager geo;
			try {
				geo = GeoLocationManager.getInstance(this.getApplicationContext());
			} catch (NoLocationProviderException e2) {
				Log.e(LOG_TAG, e2.toString() + ", msg=" + e2.getMessage());
				return;
			}
			
			Location currLocation;
			
			currLocation = geo.getMyLocation();
			GeoPoint gpsPoint = new GeoPoint((int) (currLocation.getLatitude() * 1000000), (int) (currLocation.getLongitude() * 1000000));
	        
	        mMapController.animateTo(gpsPoint);
	        mMapController.setZoom(12);
	        
	        mEngine = QueryEngine.getInstance(this.getApplicationContext());
	        
	        mapOverlays = mMapView.getOverlays();
	        drawable = this.getResources().getDrawable(R.drawable.eventbrite);
	        itemizedOverlay = new EventsOverlay(drawable, this.getApplicationContext());

	        generateOverlay();
	    }
	    
	    private void generateOverlay() {
	    	EventCollection collection = mEngine.getEvents();
	    	
	    	for (int i = 0; i< collection.getCount(); i++) {
	    		Event event = collection.getEntryAt(i);
	    		GeoPoint point = new GeoPoint((int) (event.getLatitude() * 1000000), (int) (event.getLongitude() * 1000000));
	    		OverlayItem overlayitem = new OverlayItem(point, event.getTitle(),"");
	    		itemizedOverlay.addItem(overlayitem);
		        mapOverlays.add(itemizedOverlay);
	    	}
	    }
	    
	    
	    @Override
	    protected boolean isRouteDisplayed() {
	        return false;
	    }
	     
	    
	}