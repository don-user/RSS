package ru.yundon.rss.domain

data class RssEntity(
    var title: String,
    var link: String,
    var description: String,
    var pubDate: String,
    var imageUrl: String,
    val isFavorites: Boolean = false
)
