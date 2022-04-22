package ru.yundon.rss.room.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rss_table")
data class RssEntity(
    @PrimaryKey var title: String,
    var link: String,
    var description: String,
    var pubDate: String,
    @ColumnInfo(name = "url")var imageUrl: String,
    val isFavorites: Boolean = false
)
