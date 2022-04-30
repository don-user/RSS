package ru.yundon.rss.presantation.ui.secondactivity

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.yundon.rss.data.repository.RssRepositoryImpl
import ru.yundon.rss.domain.*

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

//    private val _favorites = MutableLiveData<Boolean>()
//    val favorites: LiveData<Boolean> = _favorites

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadRssInfo(nameNews: String){
        _isLoading.value = true
        viewModelScope.launch {
            _errorConnection.postValue(loadDataUseCase.invoke(nameNews))
            _isLoading.value = false
        }

    }

    fun getListRssEntity(newsName: String){
        viewModelScope.launch() {
            _getListRss = getRssInfoUseCase.invoke(newsName) as MutableLiveData<List<RssEntity>>
        }
    }
    fun setFavoritesStatus(item: RssEntity){
        viewModelScope.launch {
            isFavorites.invoke(item)
        }
    }
}