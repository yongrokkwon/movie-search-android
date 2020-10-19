package com.ksoft.ms.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ksoft.ms.R
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subjects.PublishSubject

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val movies = arrayListOf<Movie.MovieItem>()

    private val movieSubject: PublishSubject<Movie.MovieItem> = PublishSubject.create()
    val movieFlowable: Flowable<Movie.MovieItem>
        get() = movieSubject.toFlowable(BackpressureStrategy.LATEST)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = movies.size

    fun updateMovieItem(list: List<Movie.MovieItem>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: ViewDataBinding) : RecyclerView.ViewHolder(itemView.root)
}