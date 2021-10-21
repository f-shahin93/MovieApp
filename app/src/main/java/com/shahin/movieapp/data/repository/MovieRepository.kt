package com.shahin.movieapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahin.movieapp.data.local.model.MovieDto
import com.shahin.movieapp.data.network.MovieService
import com.shahin.movieapp.data.network.model.MoviesListDto
import com.shahin.movieapp.model.Movie
import com.shahin.movieapp.model.MovieItemList
import io.realm.Realm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieService: MovieService,
) {
    private var realm: Realm = Realm.getDefaultInstance()

    fun getMoviesListRemote(): LiveData<List<MovieItemList>> {
        val queryParam = mapOf("s" to "batman")
        val moviesList = MutableLiveData<List<MovieItemList>>()
        movieService.getMoviesList(queryParam).enqueue(object : Callback<MoviesListDto> {
            override fun onResponse(call: Call<MoviesListDto>, response: Response<MoviesListDto>) {
                if (response.isSuccessful) {
                    moviesList.postValue(response.body()?.toDomain() ?: emptyList())
                }
            }

            override fun onFailure(call: Call<MoviesListDto>, t: Throwable) {

            }

        })
        return moviesList
    }

    fun getMovieRemote(imdbID: String): LiveData<Movie?> {
        val queryParam = mapOf("i" to imdbID)

        val movie = MutableLiveData<Movie?>()
        movieService.getMovie(queryParam).enqueue(object : Callback<MovieDto> {

            override fun onResponse(call: Call<MovieDto>, response: Response<MovieDto>) {
                if (response.isSuccessful) {
                    movie.postValue(response.body()?.toDomain())
                }
            }

            override fun onFailure(call: Call<MovieDto>, t: Throwable) {

            }
        })

        return movie

    }

    fun addMovieListToDB(list: List<MovieItemList>) {
        if (realm.isInTransaction.not()) {
            realm.beginTransaction()
        }

        list.map { item ->
            if (isExistMovie(item.imdbID).not()) {
                val movie = realm.createObject(MovieItemList::class.java, item.imdbID)
                movie.run {
                    poster = item.poster
                    title = item.title
                    type = item.type
                    year = item.year
                }
            }
        }

        if (realm.isInTransaction) {
            realm.commitTransaction()
        }
    }

    fun addMovieToDB(movie: Movie) {
        if (realm.isInTransaction.not())
            realm.beginTransaction()

        if (isExistCompleteMovie(movie.runtime ?: "").not()) {
            realm.where(Movie::class.java).findAll().forEach { movieComplete ->
                if (movieComplete.imdbID.equals(movie.imdbID)) {
                    movieComplete.run {
                        actors = movie.actors
                        awards = movie.awards
                        country = movie.country
                        director = movie.director
                        genre = movie.genre
                        imdbRating = movie.imdbRating
                        language = movie.language
                        metaScore = movie.metaScore
                        plot = movie.plot
                        runtime = movie.runtime
                        writer = movie.writer
                    }
                }
            }
        }

        if (realm.isInTransaction)
            realm.commitTransaction()
    }

    private fun isExistMovie(imdbId: String): Boolean {
        if (!realm.isInTransaction)
            realm.beginTransaction()

        realm.where(Movie::class.java).findAll().map { movie ->
            if (movie.imdbID.equals(imdbId)) {
                return true
            }
        }
        return false
    }

    private fun isExistCompleteMovie(runTimeParam: String): Boolean {
        if (!realm.isInTransaction)
            realm.beginTransaction()

        realm.where(Movie::class.java).findAll().forEach { movie ->
            if (movie.runtime != null && movie.runtime.equals(runTimeParam)) {
                return true
            }
        }
        return false
    }

    fun getListFromDB(): List<MovieItemList> {
        if (realm.isInTransaction.not())
            realm.beginTransaction()

        val movieList = realm.where(MovieItemList::class.java).findAll().map { it }

        if (realm.isInTransaction)
            realm.commitTransaction()

        return movieList
    }

    fun getMovieFromDB(imdbId: String): Movie? {
        if (realm.isInTransaction.not())
            realm.beginTransaction()

        val movie = realm.where(Movie::class.java).findAll().find { it.imdbID.equals(imdbId) }

        if (realm.isInTransaction)
            realm.commitTransaction()

        return movie
    }

}