package ru.yundon.rss.presantation.ui.favorites

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.yundon.rss.data.room.database.RssDbModel
import ru.yundon.rss.data.room.repository.LocalRssRepository

class ViewModelFavorites(application: Application): AndroidViewModel(application) {

    private val repositoryFavoritesRss = LocalRssRepository(application)

    val listFavoritesRss = repositoryFavoritesRss.getFavoritesRss().asLiveData()

    fun deleteFavoritesRss(item: RssDbModel){

        viewModelScope.launch(Dispatchers.IO) {
            repositoryFavoritesRss.deleteItemFavoritesRss(item)
        }
    }
}