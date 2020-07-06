package com.shahin.movieapp.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RatingsItem {

	@SerializedName("Value")
	private String value;

	@SerializedName("Source")
	private String source;

	public String getValue(){
		return value;
	}

	public String getSource(){
		return source;
	}
}