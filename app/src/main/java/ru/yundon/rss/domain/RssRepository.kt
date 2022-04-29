package ru.yundon.rss.domain

import kotlinx.coroutines.flow.Flow

interface RssRepository {

    fun loadDataFromApi()

    fun getRssInfo(): Flow<List<RssEntity>>
}