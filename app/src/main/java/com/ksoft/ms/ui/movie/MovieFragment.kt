package com.ksoft.ms.ui.movie

import android.os.Bundle
import android.view.View
import com.ksoft.ms.R
import com.ksoft.ms.databinding.FragmentMovieBinding
import com.ksoft.ms.ui.base.BaseFragment

class MovieFragment : BaseFragment<MovieViewModel, FragmentMovieBinding>() {

    override val layoutRes = R.layout.fragment_movie
    override val viewModelClass = MovieViewModel::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}