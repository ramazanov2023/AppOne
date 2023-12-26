package com.example.appone

import android.app.Application
import com.example.appone.data.AnimeRepository
import com.example.appone.data.DefaultAnimeRepository
import com.example.appone.data.ServiceLocator

class AnimeApplication : Application() {

    val repository: AnimeRepository
        get() = ServiceLocator.createRepository(applicationContext)

    override fun onCreate() {
        super.onCreate()
        repository.updateSearchList()
    }
}



