package com.anantvirdee.localevent.ui;

import com.anantvirdee.mainbackup.R;
import com.anantvirdee.localevent.datamodel.EventCollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EventsListAdapter extends BaseAdapter {
	
	//private Context mContext = null;
	private EventCollection mCollection = null;
	private LayoutInflater mInflater = null;
	
	public EventsListAdapter (Context context, EventCollection collection) {
		//mContext = context;
		mInflater = LayoutInflater.from(context);

		mCollection = collection;
	}

	public int getCount() {
		return mCollection.getCount();
	}

	public Object getItem(int position) {
		return mCollection.getEntryAt(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.event_listitem , parent, false);
			holder = new ViewHolder();
			holder.titleText = (TextView) convertView.findViewById(R.id.titleText);
			holder.distanceText = (TextView) convertView.findViewById(R.id.distanceText);
			holder.dateText = (TextView) convertView.findViewById(R.id.startDateText);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.titleText.setText(position+1+". "+mCollection.getEntryAt(position).getTitle());
		//holder.distanceText.setText(mCollection.getEntryAt(position).getDistance() + " M");
		holder.dateText.setText("Date: "+mCollection.getEntryAt(position).getStartDate());
		return convertView;
	}
	
	static class ViewHolder {
		TextView titleText;
		TextView distanceText;
		TextView dateText;
	}
}
