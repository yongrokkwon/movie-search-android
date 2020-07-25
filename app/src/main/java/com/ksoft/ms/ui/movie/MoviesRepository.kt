package com.ksoft.ms.ui.movie;

import com.ksoft.ms.exception.Failure
import com.ksoft.ms.network.Result

interface MoviesRepository {
    fun movies(): Result<Failure, MovieEntity>
}