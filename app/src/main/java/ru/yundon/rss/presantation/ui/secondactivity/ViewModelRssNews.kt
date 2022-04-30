package ru.yundon.rss.presantation.ui.secondactivity

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.yundon.rss.data.repository.RssRepositoryImpl
import ru.yundon.rss.domain.GetRssInfoUseCase
import ru.yundon.rss.domain.IsFavoritesUseCase
import ru.yundon.rss.domain.LoadDataUseCase
import ru.yundon.rss.domain.RssEntity

class ViewModelRssNews(application: Application): AndroidViewModel(application) {

    private val repository = RssRepositoryImpl(application)
    private val getRssInfoUseCase = GetRssInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)
    private val isFavorites = IsFavoritesUseCase(repository)

    private var _getListRss = MutableLiveData<List<RssEntity>>()
    val getListRss: LiveData<List<RssEntity>>
        get() = _getListRss

    private var _errorConnection = MutableLiveData<Boolean>()
    val errorConnection: LiveData<Boolean>
        get() = _errorConnection

    private val _favorites = MutableLiveData<Boolean>()
    val favorites: LiveData<Boolean> = _favorites

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadRssInfo(nameNews: String){

        _isLoading.value = true
        viewModelScope.launch {
            _errorConnection.postValue(loadDataUseCase.invoke(nameNews))
        }
        _isLoading.value = false
    }

    fun getListRssEntity(newsName: String){
        viewModelScope.launch() {
            _getListRss = getRssInfoUseCase.invoke(newsName) as MutableLiveData<List<RssEntity>>
        }
    }

//    fun updateListForRecycle(item: ru.yundon.rss.data.database.RssEntity) {
//        val index = updateListForRecycler.indexOf(item)
//
//        val trueItem: ru.yundon.rss.data.database.RssEntity = updateListForRecycler[index].copy(isFavorites = true)
//        val falseItem: ru.yundon.rss.data.database.RssEntity = updateListForRecycler[index].copy(isFavorites = false)
//
//        if (!updateListForRecycler[index].isFavorites) {
//
//            updateListForRecycler[index] = trueItem
//
//            viewModelScope.launch(Dispatchers.IO) {
//                _favorites.postValue(true)
//                repository.insertFavoritesRssItem(updateListForRecycler[index])
//                getListRss.postValue(updateListForRecycler)
//            }
//        } else {
//            updateListForRecycler[index] = falseItem
//
//            viewModelScope.launch(Dispatchers.IO) {
//                _favorites.postValue(false)
//                repository.deleteItemFavoritesRss(item)
//                getListRss.postValue(updateListForRecycler)
//            }
//        }
//    }
}