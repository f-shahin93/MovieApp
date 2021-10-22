package com.shahin.movieapp.data.network

import com.shahin.movieapp.data.network.model.MovieDto
import com.shahin.movieapp.data.network.model.MoviesListDto
import retrofit2.http.*

interface MovieService {

    @GET("?apikey=3e974fca")
    suspend fun getMoviesList(@QueryMap movieQueries: Map<String, String>): MoviesListDto

    @GET("?apikey=3e974fca")
    suspend fun getMovie(@QueryMap movieQueries: Map<String, String>): MovieDto

}