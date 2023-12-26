package com.example.appone.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://shikimori.one"

interface ShikimoriServiceApi{

    @GET("/api/animes")
    fun updateSearchList(
        @Query("limit") limit:Int,
        @Query("season") season:String,
    ): Deferred<List<Anime>>

}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

object RemoteAnimeResource {
    private var retrofitService :ShikimoriServiceApi? = null
    fun getService():ShikimoriServiceApi{
        return retrofitService ?: synchronized(this){
            retrofitService = retrofit.create(ShikimoriServiceApi::class.java)
            return retrofitService as ShikimoriServiceApi
        }
    }

    val animeService:ShikimoriServiceApi by lazy {
        retrofit.create(ShikimoriServiceApi::class.java)
    }
}