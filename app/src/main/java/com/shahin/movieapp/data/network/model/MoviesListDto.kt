package com.shahin.movieapp.data.network.model

import com.google.gson.annotations.SerializedName

data class MoviesListDto(
    @SerializedName("Response")
    var response: String,
    @SerializedName("totalResults")
    val totalResults: String,
    @SerializedName("Search")
    val movieList: List<MovieItemListDto>
)