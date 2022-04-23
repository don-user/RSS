package ru.yundon.rss.presantation.ui.secondactivity

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsRecyclerActivityViewModelFactory(private val application: Application): ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelNewsRecyclerActivity(application) as T
    }
}