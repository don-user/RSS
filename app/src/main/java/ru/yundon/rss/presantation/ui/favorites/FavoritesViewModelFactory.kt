package ru.yundon.rss.presantation.ui.favorites

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavoritesViewModelFactory(private val application: Application): ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelFavorites(application) as T
    }
}