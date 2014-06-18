package com.example.imagegopher;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ImageShowcaseActivity extends Activity {
	public static final String SEARCH_KEY = "searchString";
	public static final String ADV_SETTINGS_KEY = "result	";
	public static final String IMAGE_URL_KEY = "imageUrl";
	private static final String BASE_URL = "https://ajax.googleapis.com/ajax/services/search/images";

	static final int SETTINGS_REQ_CODE = 1;
	private static AsyncHttpClient client = new AsyncHttpClient();

	private static List<ImageObject> dataList = new ArrayList<ImageObject>();

	GridView gridView;
	ArrayAdapter<ImageObject> adapter;
	String query;
	UserImageSettings userSettings;
	EditText etSearch;
	SearchView searchView;

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_showcase);
		Intent i = getIntent();
		query = i.getStringExtra(SEARCH_KEY);
		getImages(BASE_URL, requestParams(query, 0));
		gridView = (GridView) findViewById(R.id.gridView1);
		setUpGridView();
		dataList.clear();
		adapter.clear();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.showcase_menu, menu);
		MenuItem searchItem = menu.findItem(R.id.miSearch);
		searchView = (SearchView) searchItem.getActionView();
		searchView.setOnQueryTextListener(new OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String queryStr) {
				query = queryStr;
				dataList.clear();
				adapter.clear();
				getImages(BASE_URL, requestParams(query, 0));
				setUpGridView();
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				Log.d("showcase oncreatemenu", "onQueryTextChange");
				return false;
			}
		});
		return true;
	}

	public void setUpGridView() {
		adapter = new ImageResultAdapter(this, dataList);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getApplicationContext(),
						ImageFullViewActivity.class);
				i.putExtra(IMAGE_URL_KEY, dataList.get(position).getImageUrl());
				startActivity(i);
			}
		});

		gridView.setOnScrollListener(new EndlessScrollListener() {
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				getImages(BASE_URL, requestParams(query, totalItemsCount));
			}
		});
	}

	public void getImages(String url, RequestParams params) {
		if (isNetworkAvailable()) {
			Log.d("Network connection", "Available");
			client.get(url, params, new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(JSONObject obj) {
					JSONArray arrayOfObj = null;
					try {
						arrayOfObj = obj.getJSONObject("responseData")
								.getJSONArray("results");
						adapter.addAll(ImageObject.getData(arrayOfObj));
						Log.d("DEBUG", dataList.toString());
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});
		} else {
			Log.d("Network connection", "Not Available");
			Context context = getApplicationContext();
			CharSequence text = "Issue with Internet Connection. Please check your internet connectivity";
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}

	public void onSettingsClicked(MenuItem mi) {
		Intent i = new Intent(this, AdvanceMenuActivity.class);
		startActivityForResult(i, SETTINGS_REQ_CODE);
	}

	private RequestParams requestParams(String query, int start) {
		RequestParams params = new RequestParams();
		params.put("rsz", "8");
		params.put("start", String.valueOf(start));
		params.put("v", "1.0");
		params.put("q", Uri.encode(query));

		if (userSettings != null) {
			params.put("as_sitesearch", userSettings.getImageURL());
			params.put("imgtype", userSettings.getImageType());
			params.put("imgsz", userSettings.getImageSize());
			params.put("imgcolor", userSettings.getImageColor());
		}
		return params;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to
		if (requestCode == SETTINGS_REQ_CODE) {
			// Make sure the request was successful
			if (resultCode == RESULT_OK) {
				dataList.clear();
				userSettings = (UserImageSettings) data
						.getSerializableExtra(ADV_SETTINGS_KEY);
				Log.d("usersettings imageshowcase", userSettings.toString());
				getImages(BASE_URL, requestParams(query, 0));
				setUpGridView();
			}
		}
	}

	// public void searchImages(View view) {
	// ArrayList<String> selectedItems = ((ImageResultAdapter) adapter)
	// .getCheckedItems();
	//
	// Toast.makeText(ImageShowcaseActivity.this,
	// "Total photos selected: " + selectedItems.size(),
	// Toast.LENGTH_SHORT).show();
	//
	// Log.d(ImageShowcaseActivity.class.getSimpleName(), "Selected Items: "
	// + selectedItems.toString());
	//
	// }
}
