package ru.yundon.rss.data.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import ru.yundon.rss.data.database.RssDatabase
import ru.yundon.rss.data.database.RssDbModel

class LocalRssRepository(context: Context) {

    private val favoritesRss = RssDatabase.buildRssDatabase(context).rssDao()

    fun getFavoritesRss(): Flow<List<RssDbModel>> {
        return favoritesRss.getListRssNews()
    }

//    suspend fun insertFavoritesRssItem(item: RssDbModel){
//        favoritesRss.insertRssNewsItem(item)
//    }
//
//    suspend fun deleteItemFavoritesRss(item: RssDbModel){
//        favoritesRss.deleteItem(item)
//    }

}