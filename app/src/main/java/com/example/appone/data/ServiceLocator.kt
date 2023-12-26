package com.example.appone.data

import android.content.Context
import com.example.appone.data.local.AnimeDatabase
import com.example.appone.data.local.LocalAnimeResource
import com.example.appone.data.remote.RemoteAnimeResource

object ServiceLocator {

    fun createRepository(context: Context):AnimeRepository{
        return DefaultAnimeRepository(local = createLocalAnimeResource(context),remote = RemoteAnimeResource)
    }

    private fun createLocalAnimeResource(context: Context): LocalAnimeResource {
        val database:AnimeDatabase = createAnimeDatabase(context)
        return LocalAnimeResource(database.animeDao)
    }

    private fun createAnimeDatabase(context: Context): AnimeDatabase {
        return AnimeDatabase.getInstance(context)
    }

}