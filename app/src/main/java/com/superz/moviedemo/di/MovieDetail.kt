package com.superz.moviedemo.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.superz.moviedemo.movie.common.data.ApiMapper
import com.superz.moviedemo.movie.data.remote.model.MovieDto
import com.superz.moviedemo.movie.domain.model.Movie
import com.superz.moviedemo.movie_detail.data.mapper_impl.MovieDetailMapperImpl
import com.superz.moviedemo.movie_detail.data.remote.api.MovieDetailApiService
import com.superz.moviedemo.movie_detail.data.remote.model.MovieDetailDto
import com.superz.moviedemo.movie_detail.data.repo_impl.MovieDetailRepositoryImpl
import com.superz.moviedemo.movie_detail.domain.model.MovieDetail
import com.superz.moviedemo.movie_detail.domain.repository.MovieDetailRepository
import com.superz.moviedemo.util.K
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetail {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideMovieDetailRepository(
        movieDetailApiService: MovieDetailApiService,
        mapper: ApiMapper<MovieDetail, MovieDetailDto>,
        movieMapper: ApiMapper<List<Movie>, MovieDto>
    ): MovieDetailRepository = MovieDetailRepositoryImpl(
        movieDetailApiService = movieDetailApiService,
        apiDetailMapper = mapper,
        apiMovieMapper = movieMapper,
    )

    @Provides
    @Singleton
    fun provideMovieMapper(): ApiMapper<MovieDetail, MovieDetailDto> = MovieDetailMapperImpl()

    @Provides
    @Singleton
    fun provideMovieDetailApiService(): MovieDetailApiService {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(MovieDetailApiService::class.java)
    }

}