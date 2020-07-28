package com.ksoft.ms.ui.movie

import androidx.lifecycle.viewModelScope
import com.ksoft.ms.Event
import com.ksoft.ms.network.MovieStatus
import com.ksoft.ms.ui.base.BaseViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import timber.log.Timber

@ExperimentalCoroutinesApi
class MovieViewModel @Inject constructor(
    private val getMovies: GetMovies
) : BaseViewModel<MoviePresenter>() {

    val movieAdapter = MovieAdapter { item ->
        navDirections.value = Event(item)
    }

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
                    getPresenter()?.hideLoading()
                    if (status.result.items.isEmpty()) {
                        getPresenter()?.emptyShow()
                    } else {
                        movieAdapter.submitList(status.result.items)
                        getPresenter()?.emptyHide()
                    }
                }
                is MovieStatus.Loading -> {
                    getPresenter()?.showLoading()
                }
                is MovieStatus.Error -> {
                    getPresenter()?.hideLoading()
                    getPresenter()?.showToast(status.throwable.message)
                    getPresenter()?.emptyShow()
                    Timber.e(status.throwable)
                }
                is MovieStatus.Empty -> {
                    getPresenter()?.hideLoading()
                    getPresenter()?.emptyShow()
                }
            }
        }.launchIn(viewModelScope)
    }

}