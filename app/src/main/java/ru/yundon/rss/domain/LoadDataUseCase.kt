package ru.yundon.rss.domain

import kotlinx.coroutines.flow.Flow

class LoadDataUseCase(private val repository: RssRepository){

    suspend operator fun invoke(newsName: String) : Boolean {
        return repository.loadDataFromApi(newsName)
    }
}