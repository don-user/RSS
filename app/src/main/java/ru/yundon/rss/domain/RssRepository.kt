package ru.yundon.rss.domain

import kotlinx.coroutines.flow.Flow

interface RssRepository {

    suspend fun loadDataFromApi(newsName: String) : Boolean

    fun getRssInfo(typeNews: String): Flow<List<RssEntity>>

    suspend fun isFavorites(item: RssEntity)

    fun getFavoritesList(): Flow<List<RssEntity>>

}