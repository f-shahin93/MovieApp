package com.shahin.movieapp.model

import androidx.room.*
import io.realm.annotations.PrimaryKey

@Entity(tableName = "movie_detail")
data class Movie  (
    @PrimaryKey
    var imdbID: String,
    var title: String,
    var year: String,
    var rated: String? = null,
    var released: String? = null,
    var runtime: String? = null,
    var genre: String? = null,
    var director: String? = null,
    var writer: String? = null,
    var actors: String? = null,
    var plot: String? = null,
    var language: String? = null,
    var country: String? = null,
    var awards: String? = null,
    var poster: String,
    @Embedded
    var ratings: List<RatingsItem>? = null,
    var metaScore: String? = null,
    var imdbRating: String? = null,
    var imdbVotes: String? = null,
    var type: String,
    var dVD: String? = null,
    var boxOffice: String? = null,
    var production: String? = null,
    var website: String? = null,
    var response: String? = null
)

@Entity(tableName = "movie_item_list")
data class MovieItemList(
    @PrimaryKey
    var imdbID: String,
    var title: String,
    var year: String,
    var poster: String,
    var type: String,
)

data class RatingsItem(
    @ColumnInfo(name = "Source")
    val source: String,
    @ColumnInfo(name ="Value")
    val value: String,
)
