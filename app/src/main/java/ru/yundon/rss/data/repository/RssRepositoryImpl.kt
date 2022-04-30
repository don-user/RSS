package ru.yundon.rss.data.repository

import android.app.Application
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.yundon.rss.data.api.dto.RssItemDto
import ru.yundon.rss.data.api.response.RssApiClient
import ru.yundon.rss.data.database.RssDatabase
import ru.yundon.rss.data.mapper.RssMapper
import ru.yundon.rss.domain.RssEntity
import ru.yundon.rss.domain.RssRepository
import ru.yundon.rss.utils.TypeOfNews

class RssRepositoryImpl(application: Application): RssRepository {

    private val database = RssDatabase.buildRssDatabase(application)
    private val apiService = RssApiClient.ApiRetrofit
    private val mapper = RssMapper()

    override suspend fun loadDataFromApi(newsName: String) : Boolean {
        val listRssItemDto: List<RssItemDto> = try {
            when(newsName){
                TypeOfNews.BREAKING_NEWS.newsName -> {
                    apiService.getBreakingNews().channelDto.itemDtoList
                }
                TypeOfNews.HARDWARE_NEWS.newsName -> {
                    apiService.getHardwareNews().channelDto.itemDtoList
                }
                TypeOfNews.GADGETS_NEWS.newsName -> {
                    apiService.getGadgetsNews().channelDto.itemDtoList
                }
                TypeOfNews.SOFTWARE_NEWS.newsName -> {
                    apiService.getSoftwareNews().channelDto.itemDtoList
                }
                TypeOfNews.GAMES_NEWS.newsName -> {
                    apiService.getGameNews().channelDto.itemDtoList
                }
                else -> emptyList()
            }
        }catch (e: Exception){
            emptyList()
        }
        database.rssDao().insertRssNewsList(
            mapper.mapDtoListToDbModelList(listRssItemDto, newsName)
        )
        return listRssItemDto.isNotEmpty()
    }

    override fun getRssInfo(typeNews: String): Flow<List<RssEntity>> {
        val listRssDbModel = database.rssDao().getListRssNews(typeNews)
        return listRssDbModel.map {
            mapper.mapDbModelListToEntityList(it)
        }
    }

    override suspend fun isFavorites(item: RssEntity) {
        return database.rssDao().updateRssNewsItem(mapper.mapRssEntityToDbModel(item))
    }
}