package com.example.appone.ui.watched

import androidx.lifecycle.ViewModel
import com.example.appone.R
import com.example.appone.data.AnimeRepository
import com.example.appone.data.local.LocalAnime
import com.example.appone.data.remote.Image

class WatchedViewModel(private val repository: AnimeRepository) :ViewModel() {
    val watchedList = fakeLocalAnimeList()
}

fun fakeLocalAnimeList():List<LocalAnime>{
    val anime1 = LocalAnime(
        id = 1,
        name = "Cowboy Bebop",
        russian = "Ковбой Бибоп",
        image = Image(fake = R.drawable.anim_girl_1),
        kind = "movie",
        score = "8.7",
        episodes = 24
    )

    val anime2 = LocalAnime(
        id = 2,
        name = "Gold Boy",
        russian = "Золотой парень",
        image = Image(fake = R.drawable.dsfg_1),
        kind = "tv",
        score = "8.1",
        episodes = 24
    )

    val anime3 = LocalAnime(
        id = 3,
        name = "Blue Mars",
        russian = "Синий Марс",
        image = Image(fake = R.drawable.black_girl_1),
        kind = "tv",
        score = "7.1",
        episodes = 24
    )

    val anime4 = LocalAnime(
        id = 4,
        name = "Blood Shoot",
        russian = "Удар крови",
        image = Image(fake = R.drawable.girl_hair_1),
        kind = "tv",
        score = "8.1",
        episodes = 24
    )

    val list:List<LocalAnime> = listOf(anime1,anime2,anime3,anime4)

    return list
}