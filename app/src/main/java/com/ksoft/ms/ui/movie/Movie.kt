package com.ksoft.ms.ui.movie

data class Movie(
    val display: Int,
    val items: List<MovieItem>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int,
    val errorMessage: String?
) {
    data class MovieItem(
        val actor: String,
        val director: String,
        val image: String,
        val link: String,
        val pubDate: String,
        val subtitle: String,
        val title: String,
        val userRating: String
    )
}