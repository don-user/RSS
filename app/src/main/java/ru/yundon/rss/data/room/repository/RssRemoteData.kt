package ru.yundon.rss.data.room.repository

import ru.yundon.rss.data.api.model.RssItem
import ru.yundon.rss.data.api.response.RssApiClient
import ru.yundon.rss.data.room.database.RssEntity
import ru.yundon.rss.utils.Constants.BREAKING_NEWS
import ru.yundon.rss.utils.Constants.GADGETS_NEWS
import ru.yundon.rss.utils.Constants.GAMES_NEWS
import ru.yundon.rss.utils.Constants.HARDWARE_NEWS
import ru.yundon.rss.utils.Constants.SOFTWARE_NEWS
import java.lang.Exception

class RssRemoteData {

    suspend fun getNewsList(newsName: String?): List<RssEntity>{

        val listRssItem: List<RssItem> = try {
            when(newsName){
                BREAKING_NEWS -> RssApiClient.ApiRetrofit.getBreakingNews().channel.itemList
                HARDWARE_NEWS -> RssApiClient.ApiRetrofit.getHardwareNews().channel.itemList
                GADGETS_NEWS -> RssApiClient.ApiRetrofit.getGadgetsNews().channel.itemList
                SOFTWARE_NEWS -> RssApiClient.ApiRetrofit.getSoftwareNews().channel.itemList
                GAMES_NEWS -> RssApiClient.ApiRetrofit.getGameNews().channel.itemList
                else -> emptyList()
            }
        }catch (e: Exception){
            emptyList()
        }


        val listRssNews = listRssItem.map {
            RssEntity(
                it.title,
                it.link,
                it.description,
                it.pubDate,
                it.enclosure.url,
                isFavorites = false
            )
        }
        return listRssNews
    }
}