package com.example.mainbackup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.anantvirdee.mainbackup.R;

public class GalleryActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.gallery);

	    GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(this));
	    
//	    gridview.setOnItemClickListener(new OnItemClickListener() {
//	      			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				Toast.makeText(GalleryActivity.this, "" + position, Toast.LENGTH_SHORT).show();
//			}
//	    });
	}


}
