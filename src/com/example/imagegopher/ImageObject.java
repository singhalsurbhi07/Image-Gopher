package com.example.imagegopher;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageObject {
	private String imageUrl;
	private String imageThumbURL;
	
	public ImageObject(JSONObject obj){
		try {
			this.imageUrl=obj.getString("url");
			this.imageThumbURL=obj.getString("tbUrl");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public String getImageThumbURL() {
		return imageThumbURL;
	}
	
	public static List<ImageObject> getData(JSONArray arrayOfObj){
		List<ImageObject> results=new ArrayList<ImageObject>();
		for(int i=0;i<arrayOfObj.length();i++){
			
			try {
				results.add(new ImageObject((JSONObject) arrayOfObj.get(i)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return results;
		
	}
	
}
