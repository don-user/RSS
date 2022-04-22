package ru.yundon.rss.ui.favorites

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.yundon.rss.room.database.RssEntity
import ru.yundon.rss.room.repository.LocalRssRepository

class ViewModelFavorites(application: Application): AndroidViewModel(application) {

    private val repositoryFavoritesRss = LocalRssRepository(application)

    val listFavoritesRss = repositoryFavoritesRss.getFavoritesRss().asLiveData()

    fun deleteFavoritesRss(item: RssEntity){

        viewModelScope.launch(Dispatchers.IO) {
            repositoryFavoritesRss.deleteItemFavoritesRss(item)
        }
    }
}