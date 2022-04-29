package ru.yundon.rss.presantation.ui.secondactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.yundon.rss.data.database.RssDbModel
import ru.yundon.rss.data.repository.LocalRssRepository
import ru.yundon.rss.data.repository.RssRemoteData

class ViewModelNewsRecyclerActivity(application: Application): AndroidViewModel(application) {

    private val localRssRepository = LocalRssRepository(application)
    val listEntityRss = MutableLiveData<List<RssDbModel>>()
    private var updateListForRecycler = mutableListOf<RssDbModel>()

    private val _favorites = MutableLiveData<Boolean>()
    val favorites: LiveData<Boolean> = _favorites

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getListRssEntity(newsName: String?){
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            updateListForRecycler = RssRemoteData().getNewsList(newsName) as MutableList<RssDbModel>
            listEntityRss.postValue(updateListForRecycler)
            _isLoading.postValue(false)
        }
    }

    fun updateListForRecycle(item: RssDbModel) {
        val index = updateListForRecycler.indexOf(item)

        val trueItem: RssDbModel = updateListForRecycler[index].copy(isFavorites = true)
        val falseItem: RssDbModel = updateListForRecycler[index].copy(isFavorites = false)

        if (!updateListForRecycler[index].isFavorites) {

            updateListForRecycler[index] = trueItem

            viewModelScope.launch(Dispatchers.IO) {
                _favorites.postValue(true)
                localRssRepository.insertFavoritesRssItem(updateListForRecycler[index])
                listEntityRss.postValue(updateListForRecycler)
            }
        } else {
            updateListForRecycler[index] = falseItem

            viewModelScope.launch(Dispatchers.IO) {
                _favorites.postValue(false)
                localRssRepository.deleteItemFavoritesRss(item)
                listEntityRss.postValue(updateListForRecycler)
            }
        }
    }
}