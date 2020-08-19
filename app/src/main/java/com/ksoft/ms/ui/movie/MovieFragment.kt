package com.ksoft.ms.ui.movie

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.jakewharton.rxbinding4.appcompat.queryTextChangeEvents
import com.ksoft.ms.R
import com.ksoft.ms.databinding.FragmentMovieBinding
import com.ksoft.ms.network.Status
import com.ksoft.ms.ui.base.BaseFragment
import com.ksoft.ms.ui.extensions.hide
import com.ksoft.ms.ui.extensions.show
import com.ksoft.ms.ui.main.MainActivity
import com.ksoft.ms.ui.web.WebActivity
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MovieFragment : BaseFragment<MovieViewModel, FragmentMovieBinding>() {

    override val layoutRes = R.layout.fragment_movie
    override val viewModelClass = MovieViewModel::class

    private val movieAdapter by lazy {
        MovieAdapter { startActivity(WebActivity.createIntent(requireContext(), it.link)) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addSearchViewTextChanges()

        binding.rvMovie.adapter = movieAdapter

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.ERROR -> showErrorView(it.error)
                Status.SUCCESS -> {
                    hideLoading()
                    if (it.data != null && it.data.items.isNotEmpty()) {
                        movieAdapter.submitList(it.data.items)
                        hideEmptyView()
                    } else {
                        showEmptyView()
                    }
                }
            }
        })
    }

    private fun showErrorView(error: Error?) {
        showEmptyView()
        hideLoading()

        binding.tvEmpty.text = error?.message
        binding.ivError.show()
    }

    private fun showEmptyView() {
        binding.tvEmpty.show()
        binding.rvMovie.hide()
    }

    private fun hideEmptyView() {
        binding.ivError.hide()
        binding.tvEmpty.hide()
        binding.rvMovie.show()
    }

    private fun addSearchViewTextChanges() {
        val activity = requireActivity() as MainActivity
        val toolbar = activity.binding.toolbar
        val searchView = toolbar.menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.queryTextChangeEvents()
            .debounce {
                if (it.isSubmitted) {
                    Observable.just(it)
                } else {
                    Observable.just(it).delay(1000L, TimeUnit.MILLISECONDS)
                }
            }
            .subscribe {
                Timber.d("__SearchQuery: $it")
                if (it.queryText.isNotBlank()) {
                    viewModel.searchMovies(it.queryText.toString())
                } else {
                    GlobalScope.launch(Dispatchers.Main) { showEmptyView() }
                }
            }
    }

}