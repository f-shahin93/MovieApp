package com.shahin.movieapp.data.network

import com.shahin.movieapp.data.local.model.MovieDto
import com.shahin.movieapp.data.network.model.MoviesListDto
import retrofit2.Call
import retrofit2.http.*

interface MovieService {
    @GET("")
    fun getMoviesList(@QueryMap movieQueries: Map<String, String>): Call<MoviesListDto>

    @GET("")
    fun getMovie(@QueryMap movieQueries: Map<String, String>): Call<MovieDto>

}