package com.ksoft.ms.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ksoft.ms.network.MovieStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MovieViewModel @Inject constructor(
    private val getMovies: GetMovies
) : ViewModel() {

    val movieAdapter = MovieAdapter()

    private val _viewState: MutableStateFlow<MovieStatus> = MutableStateFlow(MovieStatus.Empty)
    val viewState: StateFlow<MovieStatus> get() = _viewState

    fun searchMovies(query: String) {
        getMovies.invoke(query).map { movieEntity ->
            if (movieEntity.items.isNotEmpty()) MovieStatus.Success(movieEntity)
            else MovieStatus.Empty
        }.onStart {
            emit(MovieStatus.Loading)
        }.catch { throwable: Throwable ->
            emit(MovieStatus.Error(throwable))
        }.onEach { status ->
            when (status) {
                is MovieStatus.Success -> {
                    movieAdapter.submitList(status.result.items)
                }
                is MovieStatus.Loading -> {

                }
                is MovieStatus.Error -> {
                    Timber.e(status.throwable)
                }
            }
        }.launchIn(viewModelScope)
    }

}