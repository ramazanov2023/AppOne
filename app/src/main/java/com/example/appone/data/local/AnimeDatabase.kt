package com.example.appone.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AnimeDao{
    @Query("SELECT * FROM watched_anime_table")
    fun getWatchedList():LiveData<List<WatchedAnime>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWatchedAnime(vararg anime:WatchedAnime)

    @Query("SELECT * FROM watch_later_anime_table")
    fun getWatchLaterList():LiveData<List<WatchLaterAnime>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWatchLaterAnime(vararg anime:WatchLaterAnime)

    @Query("SELECT * FROM search_anime_table")
    fun getSearchList():LiveData<List<SearchAnime>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchAnime(vararg anime:SearchAnime)
}

@Database(entities = [WatchedAnime::class,WatchLaterAnime::class,SearchAnime::class], version = 1, exportSchema = false)
abstract class AnimeDatabase :RoomDatabase() {
    abstract val animeDao:AnimeDao

    companion object {
        var INSTANCE:AnimeDatabase? = null
        fun getInstance(context:Context):AnimeDatabase{
            return INSTANCE ?: synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    context,
                    AnimeDatabase::class.java,
                    "shikimori_database"
                ).build()
                return INSTANCE as AnimeDatabase
            }
        }
    }
}