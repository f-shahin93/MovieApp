package com.shahin.movieapp.di

import android.content.Context
import androidx.room.Room
import com.shahin.movieapp.data.local.MovieDao
import com.shahin.movieapp.data.local.MovieDatabase
import dagger.*
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(context: Context): MovieDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "movie_db"
        ).build()

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao =
        movieDatabase.movieDao


}