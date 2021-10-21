package com.shahin.movieapp.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shahin.movieapp.data.repository.MovieRepository
import com.shahin.movieapp.model.Movie
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    fun getMovie(imdbId: String): LiveData<Movie?> {
        repository.getMovieRemote(imdbId)
        return repository.getMovieRemote(imdbId)
    }

    fun addMovieToDB(movie: Movie) {
        repository.addMovieToDB(movie)
    }

    fun getMovieFromDB(imdbId: String): Movie? {
        return repository.getMovieFromDB(imdbId)
    }


}