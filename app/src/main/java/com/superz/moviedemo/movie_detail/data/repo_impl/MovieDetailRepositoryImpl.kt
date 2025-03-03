package com.superz.moviedemo.movie_detail.data.repo_impl

import com.superz.moviedemo.movie.common.data.ApiMapper
import com.superz.moviedemo.movie.data.remote.model.MovieDto
import com.superz.moviedemo.movie.domain.model.Movie
import com.superz.moviedemo.movie_detail.data.remote.api.MovieDetailApiService
import com.superz.moviedemo.movie_detail.data.remote.model.MovieDetailDto
import com.superz.moviedemo.movie_detail.domain.model.MovieDetail
import com.superz.moviedemo.movie_detail.domain.repository.MovieDetailRepository
import com.superz.moviedemo.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieDetailRepositoryImpl(
    private val movieDetailApiService: MovieDetailApiService,
    private val apiDetailMapper: ApiMapper<MovieDetail, MovieDetailDto>,
    private val apiMovieMapper: ApiMapper<List<Movie>, MovieDto>
): MovieDetailRepository {

    override fun fetchMovieDetail(movieId: Int): Flow<Response<MovieDetail>> = flow {
        emit(Response.Loading())
        val movieDetailDto = movieDetailApiService.fetchMovieDetail(movieId)
        apiDetailMapper.mapToDomain(movieDetailDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e->
        e.printStackTrace()
        emit(Response.Error(e))
    }

    override fun fetchMovie(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = movieDetailApiService.fetchMovie()
        apiMovieMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e->
        e.printStackTrace()
        emit(Response.Error(e))
    }

}