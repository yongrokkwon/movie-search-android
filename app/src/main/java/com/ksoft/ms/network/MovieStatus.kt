package com.ksoft.ms.network

import com.ksoft.ms.ui.movie.MovieEntity

sealed class MovieStatus {
    class Success(val result: MovieEntity) : MovieStatus()
    object Loading : MovieStatus()
    class Error(val throwable: Throwable) : MovieStatus()
    object Empty : MovieStatus()
}
