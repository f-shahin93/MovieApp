package com.shahin.movieapp.viewModel

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shahin.movieapp.data.network.ItemFetcher
import com.shahin.movieapp.data.repository.MovieRepository
import com.shahin.movieapp.model.Movie
import com.shahin.movieapp.model.MovieItemList
import java.lang.NullPointerException
import java.util.ArrayList

class MainViewModel: ViewModel() {
    
    private val itemFetcher = ItemFetcher.getInstance()
    private var mMovieRepository = MovieRepository.getInstance()

    fun getLDMoviesList(): LiveData<List<MovieItemList>> = itemFetcher.getMoviesList()

    fun getMoviesList() {
        itemFetcher.getMoviesList()
    }

    fun getMovie(imdbId: String): LiveData<Movie?> {
        itemFetcher.getMovie(imdbId)
        return itemFetcher.getMovie(imdbId)
    }

    fun addListToDB(list: List<MovieItemList>) {
        mMovieRepository.addMovieListToDB(list)
    }

    fun addMovieToDB(movie: Movie) {
        mMovieRepository.addMovieToDB(movie)
    }

    fun getMoviesListFromDB(): List<MovieItemList> {
        val listMovie = mMovieRepository.getListFromDB()
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

    fun getMovieFromDB(imdbId: String): Movie? {
        return mMovieRepository.getMovieFromDB(imdbId)
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