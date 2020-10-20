package com.ksoft.ms.ui.movie

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import com.jakewharton.rxbinding4.appcompat.queryTextChangeEvents
import com.ksoft.ms.R
import com.ksoft.ms.databinding.FragmentMovieBinding
import com.ksoft.ms.network.Status
import com.ksoft.ms.ui.base.BaseFragment
import com.ksoft.ms.ui.extensions.hide
import com.ksoft.ms.ui.extensions.show
import com.ksoft.ms.ui.main.MainActivity
import com.ksoft.ms.ui.web.WebActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MovieFragment : BaseFragment<MovieViewModel, FragmentMovieBinding>() {

    override val layoutRes = R.layout.fragment_movie
    override val viewModelClass = MovieViewModel::class

    private val movieAdapter by lazy { MovieAdapter() }

    private val compositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEvent()
        setView()
        setObserve()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    private fun setView() {
        binding.rvMovie.adapter = movieAdapter
    }

    private fun setObserve() {
        viewModel.movieList.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.ERROR -> showErrorView(it.error)
                Status.SUCCESS -> {
                    hideLoading()
                    if (it.data != null && it.data.items.isNotEmpty()) {
                        movieAdapter.updateMovieItem(it.data.items)
                        hideEmptyView()
                    } else {
                        showEmptyView()
                    }
                }
            }
        })

        movieAdapter.movieFlowable.subscribe { item ->
            startActivity(WebActivity.createIntent(requireContext(), item.link))
        }
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

    private fun setEvent() {
        val activity = requireActivity() as MainActivity
        val toolbar = activity.binding.toolbar
        val searchView = toolbar.menu.findItem(R.id.menu_search).actionView as SearchView

        val tappedSearchButton = searchView.queryTextChangeEvents().filter { it.isSubmitted }
        val autoSearch = searchView.queryTextChangeEvents().filter { !it.isSubmitted }

        Observable.merge(
            tappedSearchButton,
            autoSearch.debounce(500L, TimeUnit.MILLISECONDS)
        ).observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Timber.d("__SearchQuery: $it")
                if (it.queryText.isNotBlank()) {
                    viewModel.searchMovies(it.queryText.toString())
                } else {
                    showEmptyView()
                }
            }.addTo(compositeDisposable)
    }

}