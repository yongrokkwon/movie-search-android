package com.ksoft.ms.ui.movie

import com.ksoft.ms.R
import com.ksoft.ms.ui.base.BaseListAdapter

class MovieAdapter(
    clickListener: (item: Movie.MovieItem) -> Unit
) : BaseListAdapter<Movie.MovieItem>() {

    init {
        setItemClickListener { _, item -> clickListener.invoke(item) }
    }

    override fun getItemViewTypeByItem(item: Movie.MovieItem): Int = R.layout.item_movie
}