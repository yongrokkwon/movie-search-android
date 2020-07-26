package com.ksoft.ms.ui.movie;

import com.ksoft.ms.exception.Failure
import com.ksoft.ms.network.MovieService
import com.ksoft.ms.network.Result
import com.ksoft.ms.util.NetworkHandler
import retrofit2.Call
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val movieService: MovieService
) : MoviesRepository {
    override fun movies(query: String): Result<Failure, MovieEntity> {
        return when (networkHandler.isConnected) {
            true -> request(
                movieService.movies(
                    query,
                    100 /*TODO Preference Value Setting */
                )
            ) { it }
            false -> Result.Fail(Failure.NetworkConnection)
        }
    }

    private fun <T, R> request(call: Call<T>, transform: (T) -> R): Result<Failure, R> {
        return try {
            val response = call.execute()
            val body = response.body()
            when (response.isSuccessful && body != null) {
                true -> Result.Success(transform(body))
                false -> Result.Fail(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Result.Fail(Failure.ServerError)
        }
    }

}