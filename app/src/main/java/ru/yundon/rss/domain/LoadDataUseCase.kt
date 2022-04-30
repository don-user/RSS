package ru.yundon.rss.domain

import ru.yundon.rss.utils.TypeOfNews

class LoadDataUseCase(private val repository: RssRepository){

    suspend operator fun invoke(newsName: String) : Boolean {
        return repository.loadDataFromApi(newsName)
    }
}