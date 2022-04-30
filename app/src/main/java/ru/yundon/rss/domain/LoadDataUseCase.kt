package ru.yundon.rss.domain


class LoadDataUseCase(private val repository: RssRepository){

    suspend operator fun invoke(newsName: String) : Boolean {
        return repository.loadDataFromApi(newsName)
    }
}