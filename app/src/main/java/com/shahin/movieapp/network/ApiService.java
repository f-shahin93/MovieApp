package com.shahin.movieapp.network;

import com.shahin.movieapp.model.Movie;
import com.shahin.movieapp.model.MoviesList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("/?")
    Call<MoviesList> getMoviesList(@QueryMap Map<String,String> movieQueries);

    @GET("/?")
    Call<Movie> getMovie(@QueryMap Map<String,String> movieQueries);


}
