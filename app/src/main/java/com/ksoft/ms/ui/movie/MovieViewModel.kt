package com.ksoft.ms.ui.movie

import androidx.lifecycle.ViewModel
import com.ksoft.ms.ui.base.UseCase
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovies: GetMovies
) : ViewModel() {

    val movieAdapter = MovieAdapter()

    init {
        getMovies.invoke(UseCase.None()) {
            it.fold(::error) { movieEntity -> movieAdapter.submitList(movieEntity.items) }
        }
    }

}