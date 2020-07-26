package com.ksoft.ms.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.ksoft.ms.R
import com.ksoft.ms.databinding.ActivityMainBinding
import com.ksoft.ms.ui.base.BaseActivity
import com.ksoft.ms.ui.movie.MovieFragment
import timber.log.Timber


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val layoutRes = R.layout.activity_main
    override val viewModelClass = MainViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showMovieFragment()
        createToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Timber.d("query: $query")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Timber.d("newText: $newText")
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun showMovieFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, MovieFragment())
            .commitNow()
    }

    private fun createToolbar() {
        binding.toolbar.inflateMenu(R.menu.toolbar)
    }

}