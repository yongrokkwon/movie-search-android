package com.ksoft.ms.ui.movie

import com.ksoft.ms.R
import com.ksoft.ms.ui.base.BaseListAdapter

class MovieAdapter(
    clickListener: (item: MovieEntity.Item) -> Unit
) : BaseListAdapter<MovieEntity.Item>() {

    init {
        setItemClickListener { _, item -> clickListener.invoke(item) }
    }

    override fun getItemViewTypeByItem(item: MovieEntity.Item): Int = R.layout.item_movie
}