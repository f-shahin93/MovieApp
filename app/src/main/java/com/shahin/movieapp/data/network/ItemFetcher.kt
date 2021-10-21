package com.shahin.movieapp.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahin.movieapp.data.local.model.MovieDto
import com.shahin.movieapp.data.network.model.MoviesListDto
import com.shahin.movieapp.data.network.utils.BASE_URL
import com.shahin.movieapp.data.repository.toDomain
import com.shahin.movieapp.model.Movie
import com.shahin.movieapp.model.MovieItemList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ItemFetcher {

    private var queryParam: Map<String, String>
    private var retrofit: Retrofit
    private var movieService: MovieService

    companion object {
        private val itemFetcher: ItemFetcher by lazy { ItemFetcher() }
        fun getInstance(): ItemFetcher {
            return itemFetcher
        }
    }

    init {
        queryParam = mapOf("apikey" to "3e974fca")
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieService = retrofit.create(MovieService::class.java)

    }

    fun getMoviesList(): LiveData<List<MovieItemList>> {
        val queries = mutableMapOf<String, String>()
        queries.putAll(queryParam)
        queries["s"] = "batman"

        val moviesList = MutableLiveData<List<MovieItemList>>()
        movieService.getMoviesList(queries).enqueue(object : Callback<MoviesListDto> {
            override fun onResponse(call: Call<MoviesListDto>, response: Response<MoviesListDto>) {
                if (response.isSuccessful) {
                    moviesList.postValue(response.body()?.toDomain() ?: emptyList())
                }
            }

            override fun onFailure(call: Call<MoviesListDto>, t: Throwable) {

            }


        })

        return moviesList
    }

    fun getMovie(imdbID: String): LiveData<Movie?> {
        val queries = mutableMapOf<String, String>()
        queries.putAll(queryParam)
        queries["i"] = imdbID

        val movie = MutableLiveData<Movie?>()
        movieService.getMovie(queries).enqueue(object : Callback<MovieDto> {

            override fun onResponse(call: Call<MovieDto>, response: Response<MovieDto>) {
                if (response.isSuccessful) {
                    movie.postValue(response.body()?.toDomain())
                }
            }

            override fun onFailure(call: Call<MovieDto>, t: Throwable) {

            }
        })

        return movie

    }

}