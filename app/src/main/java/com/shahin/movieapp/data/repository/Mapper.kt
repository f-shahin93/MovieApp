package com.shahin.movieapp.data.repository

import com.shahin.movieapp.data.local.model.MovieDto
import com.shahin.movieapp.data.network.model.MoviesListDto
import com.shahin.movieapp.model.Movie
import com.shahin.movieapp.model.MovieItemList

fun MoviesListDto.toDomain(): List<MovieItemList> =
    this.movieList.map(MovieDto::toItemDomain)

fun MovieDto.toDomain(): Movie =
    Movie(
        imdbID,
        title,
        year,
        rated ?: "",
        released?: "",
        runtime ?: "",
        genre ?: "",
        director ?: "",
        writer ?: "",
        actors ?: "",
        plot?: "",
        language?: "",
        country?: "",
        awards?: "",
        poster,
        ratings ?: emptyList(),
        metaScore?: "",
        imdbRating?: "",
        imdbVotes?: "",
        type,
        dVD?: "",
        boxOffice?: "",
        production?: "",
        website?: "",
        response ?: ""
    )

fun MovieDto.toItemDomain(): MovieItemList = MovieItemList(imdbID, title, year, poster, type)