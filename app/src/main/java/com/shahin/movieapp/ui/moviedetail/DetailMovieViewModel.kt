package com.shahin.movieapp.ui.moviedetail

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shahin.movieapp.data.repository.MovieRepository
import com.shahin.movieapp.model.Movie
import java.lang.NullPointerException
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val error = repository.errors

    fun getMovie(imdbId: String): LiveData<Movie?> {
        repository.fetchMovie(imdbId)
        return repository.getMovie(imdbId)
    }

    fun isOnline(context: Context): Boolean {
        return try {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            netInfo != null && netInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            false
        }
    }

}