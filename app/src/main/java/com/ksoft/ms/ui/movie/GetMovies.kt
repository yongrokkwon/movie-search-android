package com.ksoft.ms.ui.movie

import com.ksoft.ms.ui.base.UseCase
import javax.inject.Inject

class GetMovies @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCase<MovieEntity, UseCase.None>() {
    override suspend fun run(params: None) = moviesRepository.movies()
}