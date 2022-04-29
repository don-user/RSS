package ru.yundon.rss.domain

class IsFavoritesUseCase(private val repository: RssRepository) {

    suspend operator fun invoke(item: RssEntity){
        repository.isFavorites(item)
    }
}