package com.shahin.movieapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.shahin.movieapp.data.local.MovieDao
import com.shahin.movieapp.data.local.MovieEntity
import com.shahin.movieapp.data.local.MovieItemListEntity
import com.shahin.movieapp.data.network.MovieService
import com.shahin.movieapp.model.Movie
import com.shahin.movieapp.model.MovieItemList
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao
) {

    val errors = MutableLiveData<Throwable>()

    fun getMovieList(): LiveData<List<MovieItemList>> =
        Transformations.map(
            movieDao.getMovieList(),
            List<MovieItemListEntity>::toMovieListDomain
        ) as MutableLiveData<List<MovieItemList>>

    fun fetchMovieList() {
        CoroutineScope(Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable ->
            errors.postValue(throwable)
        }).launch {
            val queryParam = mapOf("s" to "batman")
            val remoteList = movieService.getMoviesList(queryParam)

            if (remoteList.movieList.isNullOrEmpty().not()) {
                movieDao.insert(remoteList.toEntity())
            }
        }
    }

    fun getMovie(imdbID: String): LiveData<Movie?> {
        val localResult = movieDao.getMovie(imdbID)
        return if (localResult.value == null){
            errors.postValue(Throwable())
            MutableLiveData(null)
        } else{
            MutableLiveData(localResult.value!!.toDomain())
        }
    }


    fun fetchMovie(imdbID: String) {
        CoroutineScope(Dispatchers.IO + CoroutineExceptionHandler { coroutineContext, throwable ->
            errors.postValue(throwable)
        }).launch {
            val queryParam = mapOf("i" to imdbID)
            val remoteResult = movieService.getMovie(queryParam)
            movieDao.insert(remoteResult.toEntity())
        }
    }

}