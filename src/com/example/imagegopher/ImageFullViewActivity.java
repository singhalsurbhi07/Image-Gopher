package com.example.imagegopher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.loopj.android.image.SmartImageView;

public class ImageFullViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_full_view);
		SpannableString s = new SpannableString("Gallery");
		s.setSpan(new TypefaceSpan(this, "Choco.otf"), 0, s.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		android.app.ActionBar actionBar = getActionBar();
		actionBar.setTitle(s);
		Intent i = getIntent();
		String imageUrl = i.getStringExtra(ImageShowcaseActivity.IMAGE_URL_KEY);
		SmartImageView imageView = (SmartImageView) findViewById(R.id.smartImageView);
		imageView.setImageUrl(imageUrl);
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ImageView ivImage = (ImageView) findViewById(R.id.smartImageView);
				// Get access to the URI for the bitmap
				Uri bmpUri = getLocalBitmapUri(ivImage);
				if (bmpUri != null) {
					// Construct a ShareIntent with link to image
					Intent shareIntent = new Intent();
					shareIntent.setAction(Intent.ACTION_SEND);
					shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
					shareIntent.setType("image/*");
					// Launch sharing dialog for image
					startActivity(Intent.createChooser(shareIntent,
							"Share Image"));
				} else {
					// ...sharing failed, handle error
				}

			}

		});
	}

	public Uri getLocalBitmapUri(ImageView imageView) {
		// Extract Bitmap from ImageView drawable
		Drawable drawable = imageView.getDrawable();
		Bitmap bmp = null;
		if (drawable instanceof BitmapDrawable) {
			bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
		} else {
			return null;
		}
		// Store image to default external storage directory
		Uri bmpUri = null;
		try {
			File file = new File(
					Environment
							.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
					"share_image_" + System.currentTimeMillis() + ".png");
			file.getParentFile().mkdirs();
			FileOutputStream out = new FileOutputStream(file);
			bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.close();
			bmpUri = Uri.fromFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bmpUri;
	}
}
