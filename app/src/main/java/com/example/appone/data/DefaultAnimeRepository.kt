package com.example.appone.data

import androidx.lifecycle.LiveData
import com.example.appone.data.local.WatchedAnime
import com.example.appone.data.local.LocalAnimeResource
import com.example.appone.data.local.SearchAnime
import com.example.appone.data.remote.RemoteAnimeResource
import com.example.appone.data.remote.toSearchAnime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DefaultAnimeRepository(val local: LocalAnimeResource, val remote: RemoteAnimeResource) :
    AnimeRepository {

    override fun getWatchedAnimeList(): LiveData<List<WatchedAnime>> {
        return local.animeDao.getWatchedList()
    }

    override fun getSearchAnimeList(): LiveData<List<SearchAnime>> {
        return local.animeDao.getSearchList()
    }

    override fun updateSearchList() {
        CoroutineScope(Dispatchers.IO).launch {
            val deferredResult = remote.animeService.updateSearchList(limit = 10, season = "2023")
            try {
                val result = deferredResult.await()
                local.animeDao.insertSearchAnime(*result.toSearchAnime())
            } catch (e: Throwable) {

            }
        }
    }




}