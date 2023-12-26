package com.example.appone.data.remote


data class Anime(
    val id:Int? = 0,
    val name:String? = "",
    val russian:String? = "",
    val image: Image? = null,
    val kind:String? = "",
    val score:String? = "",
    val episodes:Int? = 0,
    val aired_on:String? = "",
)

data class Image(
    val original:String? = "",
    val fake:Int = 0
)
