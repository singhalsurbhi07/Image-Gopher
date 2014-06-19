package com.example.imagegopher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class AdvanceMenuActivity extends Activity {

	private static List<String> imageColors = new ArrayList<String>();
	private static List<String> imageSizes = new ArrayList<String>();
	private static List<String> imageTypes = new ArrayList<String>();
	Button spType;
	Spinner spSize;
	Spinner spColor;
	EditText etUrl;
	Button btnSearch;
	TextView tvSelectedType;
	String selectedType = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advance_menu);
		addResources();
		setViews();
		ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, imageTypes);
		typeAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// spType.setAdapter(typeAdapter);
		ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, imageSizes);
		sizeAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spSize.setAdapter(sizeAdapter);
		ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, imageColors);
		colorAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spColor.setAdapter(colorAdapter);
		addListenerOnButton();

	}

	private void setViews() {
		spType = (Button) findViewById(R.id.btnType);
		spSize = (Spinner) findViewById(R.id.spSize);
		spColor = (Spinner) findViewById(R.id.spColor);
		etUrl = (EditText) findViewById(R.id.etUrl);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		tvSelectedType = (TextView) findViewById(R.id.tvSelectedType);

	}

	private void addResources() {
		String[] colors = new String[]{"all", "red", "blue", "green", "black",
				"brown", "gray", "green", "oragne", "pink", "purple", "teal",
				"white", "yellow"};
		String[] sizes = new String[]{"all", "icon", "small", "medium",
				"large", "xlarge", "xxlarge", "huge"};
		String[] types = new String[]{"all", "face", "photo", "clipart",
				"lineart"};
		Collections.addAll(imageColors, colors);
		Collections.addAll(imageSizes, sizes);
		Collections.addAll(imageTypes, types);
	}

	public void btnTypeClicked(View view) {
		show();
		Log.d("Value set", selectedType);
		TextView tvSelectedType = (TextView) findViewById(R.id.tvSelectedType);
		tvSelectedType.setText(selectedType);
	}

	public void addListenerOnButton() {

		btnSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Toast.makeText(AdvanceMenuActivity.this,
				// "OnClickListener : " +
				// "\nSpinner 1 : "+ String.valueOf(spType.getSelectedItem()) +
				// "\nSpinner 2 : "+ String.valueOf(spSize.getSelectedItem()),
				// Toast.LENGTH_SHORT).show();
				// UserImageSettings userSettings = new
				// UserImageSettings(spType.getSelectedItem().toString(),
				// spSize.getSelectedItem().toString(),
				// spColor.getSelectedItem().toString(),
				// etUrl.getText().toString());
				Intent returnIntent = new Intent();
				// returnIntent.putExtra(ImageShowcaseActivity.ADV_SETTINGS_KEY,userSettings);
				setResult(RESULT_OK, returnIntent);
				finish();
			}

		});
	}

	public void show() {
		final String[] types = new String[]{"all", "face", "photo", "clipart",
				"lineart"};

		final Dialog d = new Dialog(AdvanceMenuActivity.this);
		d.setTitle("Select the Type");
		d.setContentView(R.layout.activity_dialog);
		Button b1 = (Button) d.findViewById(R.id.btnSet);
		// Button b2 = (Button) d.findViewById(R.id.button2);
		final NumberPicker np = (NumberPicker) d
				.findViewById(R.id.numberPicker1);
		np.setMaxValue(4);
		np.setDisplayedValues(types);
		np.setMinValue(0);
		np.setWrapSelectorWheel(false);
		np.setOnValueChangedListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				// TODO Auto-generated method stub
				Log.d("OnValueChanged", "Number Picker Changed");

			}
		});
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int i = np.getValue();
				selectedType = types[i];
				Log.d("npValue", selectedType);
				// tv.setText(String.valueOf(np.getValue()));
				d.dismiss();
				tvSelectedType.setText(selectedType);
			}
		});

		d.show();

	}

}
