package com.shahin.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shahin.movieapp.model.Movie
import com.shahin.movieapp.model.MovieItemList

@Database(entities = [Movie::class, MovieItemList::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}