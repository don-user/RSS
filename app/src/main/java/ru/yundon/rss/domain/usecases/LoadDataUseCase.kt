package ru.yundon.rss.domain.usecases

import ru.yundon.rss.domain.RssRepository


class LoadDataUseCase(private val repository: RssRepository){

    suspend operator fun invoke(newsName: String) : Boolean {
        return repository.loadDataFromApi(newsName)
    }
}