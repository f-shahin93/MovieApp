package com.shahin.movieapp.viewModel

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shahin.movieapp.data.repository.MovieRepository
import com.shahin.movieapp.model.MovieItemList
import java.lang.NullPointerException
import java.util.ArrayList
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    fun getLDMoviesList(): LiveData<List<MovieItemList>> = repository.getMoviesListRemote()

    fun getMoviesList() {
        repository.getMoviesListRemote()
    }

    fun addListToDB(list: List<MovieItemList>) {
        repository.addMovieListToDB(list)
    }

    fun getMoviesListFromDB(): List<MovieItemList> {
        val listMovie = repository.getListFromDB()
        if (listMovie.size > 0) {
            val movieItemList: MutableList<MovieItemList> = ArrayList<MovieItemList>()
            for (i in listMovie.indices) {
                val movie = MovieItemList(
                    imdbID = listMovie[i].imdbID,
                    title = listMovie[i].title,
                    year = listMovie[i].year,
                    poster = listMovie[i].poster,
                    type = listMovie[i].type
                )
                movieItemList.add(movie)
            }
            return movieItemList
        }
        return ArrayList<MovieItemList>()
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