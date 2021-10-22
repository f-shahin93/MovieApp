package com.shahin.movieapp.data.local

import androidx.room.*

@Entity(tableName = "movie_detail")
data class MovieEntity(
    @PrimaryKey()
    @ColumnInfo(name = "imdb_id")
    val imdbID: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "year")
    val year: String,
    @ColumnInfo(name = "rated")
    val rated: String,
    @ColumnInfo(name = "released")
    val released: String,
    @ColumnInfo(name = "runtime")
    val runtime: String,
    @ColumnInfo(name = "genre")
    val genre: String,
    @ColumnInfo(name = "director")
    val director: String,
    @ColumnInfo(name = "writer")
    val writer: String,
    @ColumnInfo(name = "actors")
    val actors: String,
    @ColumnInfo(name = "plot")
    val plot: String,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "awards")
    val awards: String,
    @ColumnInfo(name = "poster")
    val poster: String,

    //val ratings: List<RatingsItemEntity>,
    @ColumnInfo(name = "metaScore")
    val metaScore: String,
    @ColumnInfo(name = "imdb_rating")
    val imdbRating: String,
    @ColumnInfo(name = "imdb_votes")
    val imdbVotes: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "dvd")
    val dVD: String,
    @ColumnInfo(name = "box_office")
    val boxOffice: String,
    @ColumnInfo(name = "production")
    val production: String,
    @ColumnInfo(name = "website")
    val website: String,
    @ColumnInfo(name = "response")
    val response: String
)

@Entity(tableName = "movie_item_list")
data class MovieItemListEntity(
    @PrimaryKey
    @ColumnInfo(name = "imdb_id")
    val imdbID: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "year")
    val year: String,
    @ColumnInfo(name = "poster")
    val poster: String,
    @ColumnInfo(name = "type")
    val type: String,
)

data class RatingsItemEntity(
    @ColumnInfo(name = "source")
    val source: String,
    @ColumnInfo(name = "value")
    val value: String,
)
