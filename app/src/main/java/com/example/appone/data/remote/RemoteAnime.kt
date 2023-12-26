package com.example.appone.data.remote

import com.example.appone.R

data class RemoteAnime(
    val id:Int?,
    val name:String? = "",
    val russian:String? = "",
    val image:Image? = null,
    val kind:String? = "",
    val score:String? = "",
    val episodes:Int?,
    val aired_on:String? = "",
)


