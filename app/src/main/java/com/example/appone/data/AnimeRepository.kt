package com.example.appone.data

import androidx.lifecycle.LiveData
import com.example.appone.data.local.SearchAnime
import com.example.appone.data.local.WatchedAnime
import com.example.appone.data.remote.RemoteAnime

interface AnimeRepository {
    fun getWatchedAnimeList(): LiveData<List<WatchedAnime>>

    fun getSearchAnimeList(): LiveData<List<SearchAnime>>

    fun updateSearchList()
}