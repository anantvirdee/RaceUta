package com.parse.starter;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.anantvirdee.mainbackup.R;
public class FanWallActivity extends ListActivity {

	private ArrayList<Note> posts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fanwall);
		posts = new ArrayList<Note>();
		ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this, R.layout.list_item_layout, posts);
		setListAdapter(adapter);
		refreshPostList();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		Intent inn = getIntent();
		Bundle b = inn.getExtras();
		CharSequence username=null;
		if(b!=null){
			username = b.getCharSequence("username");
		}   
		switch (id) {

		case R.id.action_refresh: {
			refreshPostList();
			break;
		}

		case R.id.action_new: {
			Intent intent = new Intent(this, AddActivity.class);
			//	        intent.putExtra("username",username.toString());
			startActivity(intent);

			break;
		}
		case R.id.action_settings: {
			// Do something when user selects Settings from Action Bar overlay
			break;
		}
		}

		return super.onOptionsItemSelected(item);
	}

	private void refreshPostList() {

		ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
		// setProgressBarIndeterminateVisibility(true);
		query.findInBackground(new FindCallback<ParseObject>() {


			public void done(List<ParseObject> postList, ParseException e) {
				if (e == null) {
					// If there are results, update the list of posts
					// and notify the adapter
					posts.clear();
					for (ParseObject post : postList) {
						Note note = new Note(post.getString("Name")+":", post.getString("Comment"));
						posts.add(note);
					}
					((ArrayAdapter<Note>) getListAdapter()).notifyDataSetChanged();

				} else {
					Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
				}
			}
		});
	}
}
