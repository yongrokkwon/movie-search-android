package com.ksoft.ms.ui.movie

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import com.jakewharton.rxbinding4.appcompat.queryTextChanges
import com.ksoft.ms.EventObserver
import com.ksoft.ms.R
import com.ksoft.ms.databinding.FragmentMovieBinding
import com.ksoft.ms.ui.base.BaseFragment
import com.ksoft.ms.ui.base.BasePresenter
import com.ksoft.ms.ui.extensions.hide
import com.ksoft.ms.ui.extensions.show
import com.ksoft.ms.ui.main.MainActivity
import com.ksoft.ms.ui.web.WebActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class MovieFragment : BaseFragment<MovieViewModel, FragmentMovieBinding>(), MoviePresenter {

    override val layoutRes = R.layout.fragment_movie
    override val viewModelClass = MovieViewModel::class
    override fun initPresenter() {
        viewModel.setPresenter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addSearchViewTextChanges()
        viewModel.navDirections.observe(
            viewLifecycleOwner,
            EventObserver {
                if (it is MovieEntity.Item) {
                    startActivity(WebActivity.createIntent(requireContext(), it.link))
                }
            })
    }

    override fun emptyShow() {
        binding.tvEmpty.show()
        binding.rvMovie.hide()
    }

    override fun emptyHide() {
        binding.tvEmpty.hide()
        binding.rvMovie.show()
    }

    private fun addSearchViewTextChanges() {
        val activity = requireActivity() as MainActivity
        val toolbar = activity.binding.toolbar
        val searchView = toolbar.menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.queryTextChanges()
            .throttleWithTimeout(3000L, TimeUnit.MILLISECONDS)
            .subscribe {
                Timber.d("__SearchQuery: $it")
                if (it.isNotBlank()) {
                    viewModel.searchMovies(it.toString())
                } else {
                    GlobalScope.launch(Dispatchers.Main) { emptyShow() }
                }
            }
    }

}