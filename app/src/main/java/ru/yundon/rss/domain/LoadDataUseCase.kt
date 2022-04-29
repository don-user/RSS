package ru.yundon.rss.domain

import kotlinx.coroutines.flow.Flow

class LoadDataUseCase(private val repository: RssRepository){

    operator fun invoke(newsName: String) = repository.loadDataFromApi()
}