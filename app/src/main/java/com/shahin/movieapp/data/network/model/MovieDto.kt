package com.shahin.movieapp.data.local.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("imdbID")
    var imdbID: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("Rated")
    val rated: String?,
    @SerializedName("Released")
    val released: String?,
    @SerializedName("Runtime")
    val runtime: String?,
    @SerializedName("Genre")
    val genre: String?,
    @SerializedName("Director")
    val director: String?,
    @SerializedName("Writer")
    val writer: String?,
    @SerializedName("Actors")
    val actors: String?,
    @SerializedName("Plot")
    val plot: String?,
    @SerializedName("Language")
    val language: String?,
    @SerializedName("Country")
    val country: String?,
    @SerializedName("Awards")
    val awards: String?,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Ratings")
    val ratings: List<RatingsItemDto>?,
    @SerializedName("Metascore")
    val metaScore: String?,
    @SerializedName("imdbRating")
    val imdbRating: String?,
    @SerializedName("imdbVotes")
    val imdbVotes: String?,
    @SerializedName("Type")
    val type: String,
    @SerializedName("DVD")
    val dVD: String?,
    @SerializedName("BoxOffice")
    val boxOffice: String?,
    @SerializedName("Production")
    val production: String?,
    @SerializedName("Website")
    val website: String?,
    @SerializedName("Response")
    val response: String?
)

data class RatingsItemDto(
    @SerializedName("Source")
    val source: String,
    @SerializedName("Value")
    val value: String,
)

//"Title": "Batman Begins",
//"Year": "2005",
//"Rated": "PG-13",
//"Released": "15 Jun 2005",
//"Runtime": "140 min",
//"Genre": "Action, Adventure",
//"Director": "Christopher Nolan",
//"Writer": "Bob Kane, David S. Goyer, Christopher Nolan",
//"Actors": "Christian Bale, Michael Caine, Ken Watanabe",
//"Plot": "After training with his mentor, Batman begins his fight to free crime-ridden Gotham City from corruption.",
//"Language": "English, Mandarin",
//"Country": "United Kingdom, United States",
//"Awards": "Nominated for 1 Oscar. 13 wins & 79 nominations total",
//"Poster": "https://m.media-amazon.com/images/M/MV5BOTY4YjI2N2MtYmFlMC00ZjcyLTg3YjEtMDQyM2ZjYzQ5YWFkXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg",
//"Ratings": [
//{
//    "Source": "Internet Movie Database",
//    "Value": "8.2/10"
//},
//{
//    "Source": "Rotten Tomatoes",
//    "Value": "84%"
//},
//{
//    "Source": "Metacritic",
//    "Value": "70/100"
//}
//],
//"Metascore": "70",
//"imdbRating": "8.2",
//"imdbVotes": "1,362,365",
//"imdbID": "tt0372784",
//"Type": "movie",
//"DVD": "18 Oct 2005",
//"BoxOffice": "$206,852,432",
//"Production": "N/A",
//"Website": "N/A",
//"Response": "True"

