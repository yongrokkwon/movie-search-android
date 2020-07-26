package com.ksoft.ms.ui.movie

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import com.jakewharton.rxbinding4.appcompat.queryTextChanges
import com.ksoft.ms.R
import com.ksoft.ms.databinding.FragmentMovieBinding
import com.ksoft.ms.ui.base.BaseFragment
import com.ksoft.ms.ui.main.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class MovieFragment : BaseFragment<MovieViewModel, FragmentMovieBinding>() {

    override val layoutRes = R.layout.fragment_movie
    override val viewModelClass = MovieViewModel::class
    override fun initPresenter() {
        viewModel.setPresenter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addSearchViewTextChanges()
    }

    private fun addSearchViewTextChanges() {
        val activity = requireActivity() as MainActivity
        val toolbar = activity.binding.toolbar
        val searchView = toolbar.menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.queryTextChanges()
            .throttleWithTimeout(500L, TimeUnit.MILLISECONDS)
            .subscribe {
                Timber.d("__SearchQuery: $it")
                if (it.isNotBlank()) viewModel.searchMovies(it.toString())
            }
    }

}