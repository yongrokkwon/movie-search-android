package com.ksoft.ms.ui.movie;

import com.ksoft.ms.network.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : MoviesRepository {
    override fun movies(query: String): Flow<MovieEntity> = flow {
        val response = movieService.searchMovies(query, 100 /*TODO Preference Value Setting */)
        emit(response)
    }
}