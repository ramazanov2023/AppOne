package com.example.appone.data.remote

import com.example.appone.data.local.SearchAnime


fun List<Anime>.toSearchAnime():Array<SearchAnime>{
    return this.map {
        SearchAnime(
            id = it.id,
            name = it.name,
            russian = it.russian,
            image = BASE_URL + it.image?.original,
            kind = it.kind,
            score = it.score,
            episodes = it.episodes,
            aired_on = it.aired_on
        )
    }.toTypedArray()
}