package ru.yundon.rss.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData

class GetRssInfoUseCase(private val repository: RssRepository) {

    operator fun invoke(typeNews: String): LiveData<List<RssEntity>> {
        return repository.getRssInfo(typeNews).asLiveData()
    }
}