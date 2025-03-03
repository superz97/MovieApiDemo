package com.superz.moviedemo.movie_detail.domain.repository

import com.superz.moviedemo.movie.domain.model.Movie
import com.superz.moviedemo.movie_detail.domain.model.MovieDetail
import com.superz.moviedemo.util.Response
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun fetchMovieDetail(movieId: Int): Flow<Response<MovieDetail>>
    fun fetchMovie(): Flow<Response<List<Movie>>>
}