package ru.yundon.rss.domain

class IsFavoritesUseCase(private val repository: RssRepository) {

    suspend fun isFavorites (item: RssEntity){
        repository.isFavorites(item)
    }

    fun getFavoritesList() = repository.getFavoritesList()

}