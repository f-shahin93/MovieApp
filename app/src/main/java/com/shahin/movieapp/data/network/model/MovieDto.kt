package com.shahin.movieapp.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("imdbID")
    val imdbID: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("Rated")
    val rated: String,
    @SerializedName("Released")
    val released: String,
    @SerializedName("Runtime")
    val runtime: String,
    @SerializedName("Genre")
    val genre: String,
    @SerializedName("Director")
    val director: String,
    @SerializedName("Writer")
    val writer: String,
    @SerializedName("Actors")
    val actors: String,
    @SerializedName("Plot")
    val plot: String,
    @SerializedName("Language")
    val language: String,
    @SerializedName("Country")
    val country: String,
    @SerializedName("Awards")
    val awards: String,
    @SerializedName("Poster")
    val poster: String,
    //@SerializedName("Ratings")
    //val ratings: List<RatingsItemDto>,
    @SerializedName("Metascore")
    val metaScore: String,
    @SerializedName("imdbRating")
    val imdbRating: String,
    @SerializedName("imdbVotes")
    val imdbVotes: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("DVD")
    val dVD: String,
    @SerializedName("BoxOffice")
    val boxOffice: String,
    @SerializedName("Production")
    val production: String,
    @SerializedName("Website")
    val website: String,
    @SerializedName("Response")
    val response: String
)

/*data class RatingsItemDto(
    @SerializedName("Source")
    val source: String,
    @SerializedName("Value")
    val value: String,
)*/

data class MovieItemListDto(
    @SerializedName("imdbID")
    val imdbID: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Type")
    val type: String,
)


