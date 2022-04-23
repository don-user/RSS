package ru.yundon.rss.data.room.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import ru.yundon.rss.data.room.database.RssDatabase
import ru.yundon.rss.data.room.database.RssEntity

class LocalRssRepository(context: Context) {

    private val favoritesRss = RssDatabase.buildRssDatabase(context).rssDao()

    fun getFavoritesRss(): Flow<List<RssEntity>> {
        return favoritesRss.getListRssNews()
    }

    suspend fun insertFavoritesRssItem(item: RssEntity){
        favoritesRss.insertRssNewsItem(item)
    }

    suspend fun deleteItemFavoritesRss(item: RssEntity){
        favoritesRss.deleteItem(item)
    }

}