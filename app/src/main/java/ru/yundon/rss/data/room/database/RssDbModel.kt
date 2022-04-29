package ru.yundon.rss.data.room.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rss_table")
data class RssDbModel(
    @PrimaryKey var title: String,
    var link: String,
    var description: String,
    var pubDate: String,
    @ColumnInfo(name = "url")var imageUrl: String,
    val isFavorites: Boolean = false
)
