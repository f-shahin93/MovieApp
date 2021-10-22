package com.shahin.movieapp.data.repository

import com.shahin.movieapp.data.local.MovieEntity
import com.shahin.movieapp.data.local.MovieItemListEntity
import com.shahin.movieapp.data.network.model.MovieDto
import com.shahin.movieapp.data.network.model.MovieItemListDto
import com.shahin.movieapp.data.network.model.MoviesListDto
import com.shahin.movieapp.model.Movie
import com.shahin.movieapp.model.MovieItemList

fun MovieDto.toEntity(): MovieEntity =
    MovieEntity(
        imdbID,
        title,
        year,
        rated,
        released,
        runtime,
        genre,
        director,
        writer,
        actors,
        plot,
        language,
        country,
        awards,
        poster,
        metaScore,
        imdbRating,
        imdbVotes,
        type,
        dVD,
        boxOffice,
        production,
        website,
        response
    )

fun MovieEntity.toDomain(): Movie =
    Movie(
        imdbID,
        title,
        year,
        rated,
        released,
        runtime,
        genre,
        director,
        writer,
        actors,
        plot,
        language,
        country,
        awards,
        poster,
        metaScore,
        imdbRating,
        imdbVotes,
        type,
        dVD,
        boxOffice,
        production,
        website,
        response
    )

fun MoviesListDto.toEntity(): List<MovieItemListEntity> =
    this.movieList.map(MovieItemListDto::toEntity)

fun MovieItemListDto.toEntity(): MovieItemListEntity =
    MovieItemListEntity(imdbID, title, year, poster, type)

fun List<MovieItemListEntity>.toMovieListDomain(): List<MovieItemList> =
    this.map(MovieItemListEntity::toDomain)

fun MovieItemListEntity.toDomain(): MovieItemList = MovieItemList(imdbID, title, year, poster, type)
