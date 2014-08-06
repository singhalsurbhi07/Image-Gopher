package com.example.imagegopher;

//import com.example.firstapp.TypefaceSpan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SpannableString s = new SpannableString("Image Gopher");
		s.setSpan(new TypefaceSpan(this, "Choco.otf"), 0, s.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		// Update the action bar title with the TypefaceSpan instance
		android.app.ActionBar actionBar = getActionBar();
		actionBar.setTitle(s);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onSearch(View view) {
		EditText searchStr = (EditText) findViewById(R.id.srchString);
		Intent i = new Intent(this, ImageShowcaseActivity.class);
		i.putExtra(ImageShowcaseActivity.SEARCH_KEY, searchStr.getText()
				.toString());
		startActivity(i);
	}

	public void onSettingsClicked(MenuItem mi) {
		Intent i = new Intent(this, AdvanceMenuActivity.class);
		startActivity(i);
	}

}
