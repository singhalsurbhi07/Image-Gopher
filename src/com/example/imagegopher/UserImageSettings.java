package com.example.imagegopher;

import java.io.Serializable;

public class UserImageSettings implements Serializable {

	private String imageType;
	private String imageSize;
	private String imageColor;
	private String imageURL;

	public UserImageSettings(String imageType, String imageSize,
			String imageColor, String imageURL) {
		// this.searchString = searchString;
		this.imageType = imageType;
		this.imageSize = imageSize;
		this.imageColor = imageColor;
		this.imageURL = imageURL;
	}

	public String toString() {
		return String.format("%s.%s.%s.%s", imageType, imageSize, imageColor,
				imageURL);
	}

	public String getImageType() {
		return imageType;
	}

	public String getImageSize() {
		return imageSize;
	}

	public String getImageColor() {
		return imageColor;
	}

	public String getImageURL() {
		return imageURL;
	}
}
