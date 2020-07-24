package com.ksoft.ms.ui.main

import android.os.Bundle
import com.ksoft.ms.R
import com.ksoft.ms.databinding.ActivityMainBinding
import com.ksoft.ms.ui.base.BaseActivity
import com.ksoft.ms.ui.movie.MovieFragment

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val layoutRes = R.layout.activity_main
    override val viewModelClass = MainViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showMovieFragment()
    }

    private fun showMovieFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, MovieFragment())
            .commitNow()
    }

}