package com.superz.moviedemo.movie_detail.data.remote.api

import com.superz.moviedemo.BuildConfig
import com.superz.moviedemo.movie.data.remote.model.MovieDto
import com.superz.moviedemo.movie_detail.data.remote.model.MovieDetailDto
import com.superz.moviedemo.util.K
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val MOVIE_ID = "movie_id"

interface MovieDetailApiService {

    @GET("${K.MOVIE_DETAIL_ENDPOINT}/{$MOVIE_ID}")
    suspend fun fetchMovieDetail(
        @Path(MOVIE_ID) movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("append_to_response") appendToResponse: String = "credits,reviews"
    ): MovieDetailDto

    @GET(K.MOVIE_ENDPOINT)
    suspend fun fetchMovie(
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false
    ): MovieDto

}