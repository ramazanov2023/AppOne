package com.example.appone.ui.search

import androidx.lifecycle.ViewModel
import com.example.appone.data.AnimeRepository

class SearchViewModel(private val repository: AnimeRepository) :ViewModel() {
    val searchList = repository.getSearchAnimeList()



}
