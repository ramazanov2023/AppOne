package com.example.appone.ui.watched

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appone.data.AnimeRepository
import java.lang.IllegalArgumentException

class WatchedViewModelFactory(private val repository: AnimeRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WatchedViewModel::class.java)){
            return WatchedViewModel(repository) as T
        }
        throw IllegalArgumentException("Factory Error")
    }
}