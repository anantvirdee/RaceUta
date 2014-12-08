package uta.se1.rss.reader;

import com.anantvirdee.mainbackup.R;

import uta.se1.rss.reader.data.Item;
import uta.se1.rss.reader.listeners.ListListener;
import uta.se1.rss.reader.util.FBRssReader;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MainRSSActivity extends Activity {
	private TextView mTitle=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set view
		setContentView(R.layout.news_page);
		 mTitle = (TextView) findViewById(R.id.newstitle);
	        mTitle.setText(R.string.news_list_title);
		try {
			// Create RSS reader
			FBRssReader rssReader = new FBRssReader(
					"https://www.facebook.com/feeds/page.php?id=268589023176502&format=rss20");
			// Get a ListView from main view
			ListView FbItems = (ListView) findViewById(R.id.listMainView);

			// Create a list adapter
			ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this,
					android.R.layout.simple_list_item_1, rssReader.getItems());
			// Set list adapter for the ListView
			FbItems.setAdapter(adapter);

			// Set list view item click listener
			FbItems.setOnItemClickListener(new ListListener(rssReader
					.getItems(), this));

		} catch (Exception e) {
			Log.e("FB SAE Reader", e.getMessage());
		}

	}

}