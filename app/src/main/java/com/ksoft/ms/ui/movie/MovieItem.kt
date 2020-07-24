package com.ksoft.ms.ui.movie

import com.google.gson.annotations.SerializedName
import com.ksoft.ms.ui.base.BaseItem

data class MovieItem(
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("start")
    val start: Int,
    @SerializedName("total")
    val total: Int
) {
    data class Item(
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
    ) : BaseItem(title)
}