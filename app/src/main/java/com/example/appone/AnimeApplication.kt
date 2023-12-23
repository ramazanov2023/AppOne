package com.example.appone

import android.app.Application
import com.example.appone.data.AnimeRepository

class AnimeApplication : Application() {

    val repository: AnimeRepository
        get() = DefaultAnimeRepository()

    override fun onCreate() {
        super.onCreate()
    }
}



