package com.ksoft.ms.ui.movie

import com.ksoft.ms.R
import com.ksoft.ms.ui.base.BaseListAdapter

class MovieAdapter : BaseListAdapter<MovieItem.Item>() {
    override fun getItemViewTypeByItem(item: MovieItem.Item): Int = R.layout.item_movie
}