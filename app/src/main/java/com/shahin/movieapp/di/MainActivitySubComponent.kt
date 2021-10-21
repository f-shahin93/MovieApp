package com.shahin.movieapp.di

import com.shahin.movieapp.ui.movieList.MovieListFragment
import com.shahin.movieapp.ui.moviedetail.DetailMovieFragment
import dagger.Subcomponent

@Subcomponent
interface MainActivitySubComponent {
    fun inject(movieListFragment: MovieListFragment)
    fun inject(movieFragment: DetailMovieFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivitySubComponent
    }
}