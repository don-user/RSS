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
import ru.yundon.rss.utils.Constants.BREAKING_NEWS
import ru.yundon.rss.utils.Constants.GADGETS_NEWS
import ru.yundon.rss.utils.Constants.GAMES_NEWS
import ru.yundon.rss.utils.Constants.HARDWARE_NEWS
import ru.yundon.rss.utils.Constants.SOFTWARE_NEWS

class RssRepositoryImpl(
    private val application: Application,
): RssRepository {

    private val database = RssDatabase.buildRssDatabase(application)
    private val apiService = RssApiClient.ApiRetrofit
    private val mapper = RssMapper()

    override suspend fun loadDataFromApi(newsName: String) : Boolean {
        val listRssItemDto: List<RssItemDto> = try {
            when(newsName){
                BREAKING_NEWS -> apiService.getBreakingNews().channelDto.itemDtoList
                HARDWARE_NEWS -> apiService.getHardwareNews().channelDto.itemDtoList
                GADGETS_NEWS -> apiService.getGadgetsNews().channelDto.itemDtoList
                SOFTWARE_NEWS -> apiService.getSoftwareNews().channelDto.itemDtoList
                GAMES_NEWS -> apiService.getGameNews().channelDto.itemDtoList
                else -> emptyList()
            }
        }catch (e: Exception){
            emptyList()
        }
        database.rssDao().insertRssNewsList(mapper.mapDtoListToDbModelList(listRssItemDto))
        return listRssItemDto.isNotEmpty()
    }

    override fun getRssInfo(): Flow<List<RssEntity>> {
        val d = database.rssDao().getListRssNews()
        return d.map {
            mapper.mapDbModelListToEntityList(it)
        }
    }

    override suspend fun isFavorites(item: RssEntity) {
        TODO("Not yet implemented")
    }
}