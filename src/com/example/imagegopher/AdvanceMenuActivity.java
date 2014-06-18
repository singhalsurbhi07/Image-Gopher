package com.example.imagegopher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdvanceMenuActivity extends Activity {
	
	private static List<String> imageColors = new ArrayList<String>();
	private static List<String> imageSizes = new ArrayList<String>();
	private static List<String> imageTypes = new ArrayList<String>();
	Spinner spType;
	Spinner spSize;
	Spinner spColor;
	EditText etUrl;
	Button btnSearch;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advance_menu);
		addResources();
		setViews();
		ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, imageTypes);
			typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spType.setAdapter(typeAdapter);
		ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, imageSizes);
			sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spSize.setAdapter(sizeAdapter);
		ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, imageColors);
			colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spColor.setAdapter(colorAdapter);
		addListenerOnButton();

		
	}
	
	private void setViews(){
		spType = (Spinner) findViewById(R.id.spType);
		spSize = (Spinner) findViewById(R.id.spSize);
		spColor = (Spinner) findViewById(R.id.spColor);
		etUrl = (EditText) findViewById(R.id.etUrl);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		
	}
	
	private void addResources() {
		String [] colors = new String[]{"all", "red", "blue", "green", "black", "brown", "gray",
		"green", "oragne", "pink", "purple", "teal", "white", "yellow"};
		String [] sizes = new String[]{"all", "icon", "small", "medium", 
		"large", "xlarge", "xxlarge", "huge"};
		String [] types = new String[]{"all", "face", "photo", "clipart", "lineart"};
		Collections.addAll(imageColors, colors);
		Collections.addAll(imageSizes, sizes);
		Collections.addAll(imageTypes, types);
		}
	
	public void addListenerOnButton() {
		 
		
		btnSearch.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	 
		    Toast.makeText(AdvanceMenuActivity.this,
			"OnClickListener : " + 
	                "\nSpinner 1 : "+ String.valueOf(spType.getSelectedItem()) + 
	                "\nSpinner 2 : "+ String.valueOf(spSize.getSelectedItem()),
				Toast.LENGTH_SHORT).show();
		    UserImageSettings userSettings = new UserImageSettings(spType.getSelectedItem().toString(), spSize.getSelectedItem().toString(), spColor.getSelectedItem().toString(), etUrl.getText().toString());
		    Intent returnIntent = new Intent();
		    returnIntent.putExtra(ImageShowcaseActivity.ADV_SETTINGS_KEY,userSettings);
		    setResult(RESULT_OK,returnIntent);
		    finish();	
		  }
	 
		});
	  }
	
	
}
