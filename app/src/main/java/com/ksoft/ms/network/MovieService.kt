package com.ksoft.ms.network;

import com.ksoft.ms.ui.movie.MovieEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieService @Inject constructor(
    private val retrofit: Retrofit
) : MovieApi {
    private val movieApi by lazy { retrofit.create(MovieApi::class.java) }

    override fun movies(query: String, display: Int): Call<MovieEntity> = movieApi.movies(query, 30)
}
