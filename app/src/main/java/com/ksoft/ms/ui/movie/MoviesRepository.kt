package com.ksoft.ms.ui.movie;

import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun movies(query: String): Flow<MovieEntity>
}