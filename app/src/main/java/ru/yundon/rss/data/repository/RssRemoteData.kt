package ru.yundon.rss.data.repository

import ru.yundon.rss.data.api.dto.RssItemDto
import ru.yundon.rss.data.api.response.RssApiClient
import ru.yundon.rss.data.database.RssDbModel
import ru.yundon.rss.utils.Constants.BREAKING_NEWS
import ru.yundon.rss.utils.Constants.GADGETS_NEWS
import ru.yundon.rss.utils.Constants.GAMES_NEWS
import ru.yundon.rss.utils.Constants.HARDWARE_NEWS
import ru.yundon.rss.utils.Constants.SOFTWARE_NEWS
import java.lang.Exception

class RssRemoteData {

    suspend fun getNewsList(newsName: String?): List<RssDbModel>{

        val listRssItemDto: List<RssItemDto> = try {
            when(newsName){
                BREAKING_NEWS -> RssApiClient.ApiRetrofit.getBreakingNews().channelDto.itemDtoList
                HARDWARE_NEWS -> RssApiClient.ApiRetrofit.getHardwareNews().channelDto.itemDtoList
                GADGETS_NEWS -> RssApiClient.ApiRetrofit.getGadgetsNews().channelDto.itemDtoList
                SOFTWARE_NEWS -> RssApiClient.ApiRetrofit.getSoftwareNews().channelDto.itemDtoList
                GAMES_NEWS -> RssApiClient.ApiRetrofit.getGameNews().channelDto.itemDtoList
                else -> emptyList()
            }
        }catch (e: Exception){
            emptyList()
        }


        val listRssNews = listRssItemDto.map {
            RssDbModel(
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