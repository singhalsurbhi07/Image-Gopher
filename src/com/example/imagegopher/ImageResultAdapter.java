package com.example.imagegopher;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.loopj.android.image.SmartImageView;

public class ImageResultAdapter extends ArrayAdapter<ImageObject> {

	// ArrayList<ImageObject> mList;
	// SparseBooleanArray mSparseBooleanArray;

	public ImageResultAdapter(Context context, List<ImageObject> images) {

		super(context, R.layout.grid_element, images);
		// this.mList = (ArrayList<ImageObject>) images;
	}

	// public ArrayList<String> getCheckedItems() {
	// ArrayList<String> mTempArry = new ArrayList<String>();
	// for (int i = 0; i < mList.size(); i++) {
	// if (mSparseBooleanArray.get(i)) {
	// mTempArry.add(mList.get(i).getImageUrl());
	// }
	// }
	// return mTempArry;
	// }
	// @Override
	// public int getCount() {
	// return mList.size();
	// }
	// @Override
	// public ImageObject getItem(int position) {
	// return null;
	// }
	// @Override
	// public long getItemId(int position) {
	// return position;
	// }
	//
	// // @Override
	// // public View getView(int position, View convertView, ViewGroup parent)
	// {}
	// OnCheckedChangeListener mCheckedChangeListener = new
	// OnCheckedChangeListener() {
	// @Override
	// public void onCheckedChanged(CompoundButton buttonView,
	// boolean isChecked) {
	//
	// // TODO Auto-generated method stub
	//
	// mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
	//
	// }
	//
	// };

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageObject imageInfo = this.getItem(position);
		SmartImageView ivImage;
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			ivImage = (SmartImageView) inflator.inflate(R.layout.grid_element,
					parent, false);

		} else {
			ivImage = (SmartImageView) convertView;
			ivImage.setImageResource(android.R.color.transparent);
		}
		ivImage.setImageUrl(imageInfo.getImageThumbURL());
		// CheckBox mCheckBox = (CheckBox) convertView
		// .findViewById(R.id.checkBox1);
		//
		// mCheckBox.setTag(position);
		//
		// mCheckBox.setChecked(mSparseBooleanArray.get(position));
		//
		// mCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);

		return ivImage;
	}
}
