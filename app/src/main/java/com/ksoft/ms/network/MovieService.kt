package com.ksoft.ms.network;

import com.ksoft.ms.ui.movie.MovieEntity
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieService @Inject constructor(
    private val retrofit: Retrofit
) : MovieApi {
    private val movieApi by lazy { retrofit.create(MovieApi::class.java) }

    override suspend fun searchMovies(query: String, display: Int): MovieEntity = movieApi.searchMovies(query, 30)
}
