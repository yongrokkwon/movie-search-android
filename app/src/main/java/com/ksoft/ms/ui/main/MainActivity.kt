package com.ksoft.ms.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.jakewharton.rxbinding4.appcompat.queryTextChanges
import com.ksoft.ms.R
import com.ksoft.ms.databinding.ActivityMainBinding
import com.ksoft.ms.ui.base.BaseActivity
import com.ksoft.ms.ui.movie.MovieFragment
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import timber.log.Timber
import java.util.concurrent.TimeUnit


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


        return super.onCreateOptionsMenu(menu)
    }

    private fun showMovieFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, MovieFragment())
            .commitNow()
    }

    private fun createToolbar() {
        binding.toolbar.inflateMenu(R.menu.toolbar_search)
    }

}