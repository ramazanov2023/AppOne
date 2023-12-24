package com.example.appone.data.local

import com.example.appone.data.remote.Image

data class LocalAnime(
    val id:Int? = 0,
    val name:String? = "",
    val russian:String? = "",
    val image: Image? = null,
    val kind:String? = "",
    val score:String? = "",
    val episodes:Int? = 0,
    val aired_on:String? = "",
)

