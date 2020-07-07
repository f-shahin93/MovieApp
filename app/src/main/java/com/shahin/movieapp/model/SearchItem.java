package com.shahin.movieapp.model;

import com.google.gson.annotations.SerializedName;

public class SearchItem{

	@SerializedName("Type")
	private String type;

	@SerializedName("Year")
	private String year;

	@SerializedName("imdbID")
	private String imdbID;

	@SerializedName("Poster")
	private String poster;

	@SerializedName("Title")
	private String title;

	public String getType(){
		return type;
	}

	public String getYear(){
		return year;
	}

	public String getImdbID(){
		return imdbID;
	}

	public String getPoster(){
		return poster;
	}

	public String getTitle(){
		return title;
	}

	public SearchItem(String imdbID,String title,String year,String poster,String type){
		this.imdbID = imdbID;
		this.title = title;
		this.poster = poster;
		this.type = type;
		this.year = year;
	}

	public SearchItem(){

	}
}