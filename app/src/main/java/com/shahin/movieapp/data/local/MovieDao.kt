package com.shahin.movieapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shahin.movieapp.model.Movie
import com.shahin.movieapp.model.MovieItemList

@Dao
interface MovieDao {

    @Insert
    fun insert(movie: Movie)

    @Insert
    fun insert(list: List<MovieItemList>)

    @Query("SELECT * FROM movie_detail WHERE imdbID = :id")
    fun getMovie(id: String): LiveData<Movie?>

    @Query("SELECT * FROM movie_item_list")
    fun getMovieList(): LiveData<List<MovieItemList>?>


}