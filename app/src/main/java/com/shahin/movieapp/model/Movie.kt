package com.shahin.movieapp.model

data class Movie(
    val imdbID: String,
    val title: String,
    val year: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val language: String,
    val country: String,
    val awards: String,
    val poster: String,
    //val ratings: List<RatingsItem>,
    val metaScore: String,
    val imdbRating: String,
    val imdbVotes: String,
    val type_Movie: String,
    val dVD: String,
    val boxOffice: String,
    val production: String,
    val website: String,
    val response: String
)

data class MovieItemList(
    val imdbID: String,
    val title: String,
    val year: String,
    val poster: String,
    val type: String,
)

data class RatingsItem(
    val source: String,
    val value: String,
)
