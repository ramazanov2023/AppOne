package com.example.appone.ui.watched

import androidx.lifecycle.ViewModel
import com.example.appone.data.AnimeRepository

class WatchedViewModel(private val repository: AnimeRepository) :ViewModel() {
    val watchedList = repository
}