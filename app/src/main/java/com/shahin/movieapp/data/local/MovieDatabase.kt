package com.shahin.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class, MovieItemListEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}