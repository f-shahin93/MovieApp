package com.shahin.movieapp.data.repository

import com.shahin.movieapp.model.Movie
import com.shahin.movieapp.model.MovieItemList
import io.realm.Realm

class MovieRepository {

    private var realm: Realm = Realm.getDefaultInstance()

    companion object {
        private val repository: MovieRepository by lazy { MovieRepository() }
        fun getInstance(): MovieRepository {
            return repository
        }
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