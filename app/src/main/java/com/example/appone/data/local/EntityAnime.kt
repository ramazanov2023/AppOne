package com.example.appone.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appone.data.remote.Image

@Entity(tableName = "watched_anime_table")
data class WatchedAnime(
    @PrimaryKey
    val id:Int?,
    val name:String? = "",
    val russian:String? = "",
    val image: String? = "",
    val kind:String? = "",
    val score:String? = "",
    val episodes:Int? = 0,
    val aired_on:String? = "",
)

@Entity(tableName = "watch_later_anime_table")
data class WatchLaterAnime(
    @PrimaryKey
    val id:Int?,
    val name:String? = "",
    val russian:String? = "",
    val image: String? = "",
    val kind:String? = "",
    val score:String? = "",
    val episodes:Int? = 0,
    val aired_on:String? = "",
)

@Entity(tableName = "search_anime_table")
data class SearchAnime(
    @PrimaryKey
    val id:Int?,
    val name:String? = "",
    val russian:String? = "",
    val image: String? = "",
    val kind:String? = "",
    val score:String? = "",
    val episodes:Int? = 0,
    val aired_on:String? = "",
)

