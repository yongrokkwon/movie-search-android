package com.ksoft.ms.ui.movie

import com.ksoft.ms.ui.base.UseCase
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class GetMovies @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCase<MovieEntity, String>() {
    override fun execute(params: String): Flow<MovieEntity> = moviesRepository.movies(params)
}