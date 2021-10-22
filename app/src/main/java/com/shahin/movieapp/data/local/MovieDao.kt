package com.shahin.movieapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<MovieItemListEntity>)

    @Query("SELECT * FROM movie_detail WHERE imdb_id = :imdbId")
    fun getMovie(imdbId: String): LiveData<MovieEntity?>

    @Query("SELECT * FROM movie_item_list")
    fun getMovieList(): LiveData<List<MovieItemListEntity>>


}