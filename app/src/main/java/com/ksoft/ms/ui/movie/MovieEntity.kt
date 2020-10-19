package com.ksoft.ms.ui.movie

import com.google.gson.annotations.SerializedName

data class MovieEntity(
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val items: List<MovieItemEntity>,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("start")
    val start: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("errorMessage")
    val errorMessage: String?
) {
    data class MovieItemEntity(
        @SerializedName("actor")
        val actor: String,
        @SerializedName("director")
        val director: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("link")
        val link: String,
        @SerializedName("pubDate")
        val pubDate: String,
        @SerializedName("subtitle")
        val subtitle: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("userRating")
        val userRating: String
    )

    fun mapFrom() = Movie(
        display = display,
        items = items.map {
            Movie.MovieItem(
                actor = it.actor,
                director = it.director,
                image = it.image,
                link = it.link,
                pubDate = it.pubDate,
                subtitle = it.subtitle,
                title = it.title,
                userRating = it.userRating
            )
        },
        lastBuildDate = lastBuildDate,
        start = start,
        total = total,
        errorMessage = errorMessage
    )
}