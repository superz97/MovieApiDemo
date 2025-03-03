package com.superz.moviedemo.movie.domain.repository

import com.superz.moviedemo.movie.domain.model.Movie
import com.superz.moviedemo.util.Response
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchDiscoverMovie(): Flow<Response<List<Movie>>>
    fun fetchTrendingMovie(): Flow<Response<List<Movie>>>
}