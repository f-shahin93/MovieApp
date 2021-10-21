package com.shahin.movieapp.data.network.model

import com.google.gson.annotations.SerializedName
import com.shahin.movieapp.data.local.model.MovieDto

data class MoviesListDto(
    @SerializedName("Response")
    var response: String,
    @SerializedName("totalResults")
    val totalResults: String,
    @SerializedName("Search")
    val movieList: List<MovieDto>
)