package com.parse.starter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.anantvirdee.mainbackup.R;
public class AddActivity extends Activity {

	private Note note;
	private EditText titleEditText;
	private EditText contentEditText;
	private String postName;
	private String postComment;
	private Button saveButton;

	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_add);

		//Intent intent = this.getIntent();
		Intent inn =getIntent();
		Bundle b = inn.getExtras();
		
		titleEditText = (EditText) findViewById(R.id.noteName);
		contentEditText = (EditText) findViewById(R.id.noteComment);

		if (b!= null) {
			//note = new Note( intent.getStringExtra("noteName"), intent.getStringExtra("noteComment"));
			//Bundle b =intent.getExtras();
//			CharSequence username = b.getString("username");
			titleEditText.setText(note.getName());
			contentEditText.setText(note.getComment());
		}

		saveButton = (Button)findViewById(R.id.save);
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				saveNote();
			}

		});


	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
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
	private void saveNote() {

		postName = titleEditText.getText().toString().trim();
		postComment = contentEditText.getText().toString().trim();


		// If user doesn't enter a title or content, do nothing
		// If user enters title, but no content, save
		// If user enters content with no title, give warning
		// If user enters both title and content, save

		if (!postName.isEmpty()) {

			// Check if post is being created or edited

			if (note == null) {
				// create new post

				final ParseObject post = new ParseObject("Post");
				post.put("Name", postName);
				post.put("Comment", postComment);
				//setProgressBarIndeterminateVisibility(true);
				post.saveInBackground(new SaveCallback() {
					public void done(ParseException e) {
						//setProgressBarIndeterminateVisibility(false);
						if (e == null) {
							// Saved successfully.
							note = new Note( postName, postComment);
							Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
						} else {
							// The save failed.
							Toast.makeText(getApplicationContext(), "Failed to Save", Toast.LENGTH_SHORT).show();
							Log.d(getClass().getSimpleName(), "User update error: " + e);
						}
					}
				});

			}
			else {
				// update post

				ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");

				// Retrieve the object by id
				query.getInBackground( note.getName(), new GetCallback<ParseObject>() {
					public void done(ParseObject post, ParseException e) {
						if (e == null) {
							// Now let's update it with some new data.
							post.put("Name", postName);
							post.put("Comment", postComment);
							//setProgressBarIndeterminateVisibility(true);
							post.saveInBackground(new SaveCallback() {
								public void done(ParseException e) {
									//setProgressBarIndeterminateVisibility(false);
									if (e == null) {
										// Saved successfully.
										Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
									} else {
										// The save failed.
										Toast.makeText(getApplicationContext(), "Failed to Save", Toast.LENGTH_SHORT).show();
										Log.d(getClass().getSimpleName(), "User update error: " + e);
									}
								}
							});
						}
					}
				});
			}
		}
		else if (postName.isEmpty() && !postComment.isEmpty()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
			builder.setMessage(R.string.edit_error_message)
			.setTitle(R.string.edit_error_title)
			.setPositiveButton(android.R.string.ok, null);
			AlertDialog dialog = builder.create();
			dialog.show();
		}
	}


}
