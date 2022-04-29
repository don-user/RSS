package ru.yundon.rss.domain

import kotlinx.coroutines.flow.Flow

class GetRssInfoUseCase(private val repository: RssRepository) {

    operator fun invoke(): Flow<List<RssEntity>> {
        return repository.getRssInfo()
    }
}