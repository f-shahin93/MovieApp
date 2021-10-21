package com.shahin.movieapp.data.network

import com.shahin.movieapp.data.local.model.MovieDto
import com.shahin.movieapp.data.network.model.MoviesListDto
import retrofit2.Call
import retrofit2.http.*

interface MovieService {

    @GET("?apikey=3e974fca")
    fun getMoviesList(@QueryMap movieQueries: Map<String, String>): Call<MoviesListDto>

    @GET("?apikey=3e974fca")
    fun getMovie(@QueryMap movieQueries: Map<String, String>): Call<MovieDto>

}