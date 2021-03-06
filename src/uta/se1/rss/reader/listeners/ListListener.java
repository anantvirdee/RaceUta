package uta.se1.rss.reader.listeners;

import java.util.List;

import uta.se1.rss.reader.data.Item;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author Mohammed Fahad
 *
 */
public class ListListener implements OnItemClickListener {

	// List item's reference
	List<Item> listItems;
	// Calling activity reference
	Activity activity;
	
	public ListListener(List<Item> aListItems, Activity anActivity) {
		listItems = aListItems;
		activity  = anActivity;
	}

	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(listItems.get(pos).getLink()));
	
		activity.startActivity(i);
		
	}
	
}
