package com.ksoft.ms.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ksoft.ms.network.Resource
import com.ksoft.ms.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovies: GetMovies
) : BaseViewModel() {

    val movieList: LiveData<Resource<Movie>> get() = _movieList
    private val _movieList = MutableLiveData<Resource<Movie>>()

    fun searchMovies(query: String) {
        viewModelScope.launch(Dispatchers.Default) {
            _movieList.postValue(Resource.loading())

            getMovies.invoke(query)
                .map { it.mapFrom() }
                .onEach { _movieList.postValue(Resource.success(it)) }
                .catch { _movieList.postValue(Resource.error(Error(it.message))) }
                .launchIn(viewModelScope)
        }
    }

}