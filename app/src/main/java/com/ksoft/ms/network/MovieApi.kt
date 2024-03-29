package com.ksoft.ms.network;

import com.ksoft.ms.ui.movie.MovieEntity
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/v1/search/movie.json")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("display") display: Int
    ): MovieEntity
}
