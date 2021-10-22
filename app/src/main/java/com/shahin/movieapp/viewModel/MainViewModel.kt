package com.shahin.movieapp.viewModel

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shahin.movieapp.data.repository.MovieRepository
import com.shahin.movieapp.model.MovieItemList
import java.lang.NullPointerException
import java.util.ArrayList
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val error: LiveData<Throwable> = repository.errors

    fun getMoviesList(): LiveData<List<MovieItemList>> = repository.getMovieList()

    init {
        repository.fetchMovieList()
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