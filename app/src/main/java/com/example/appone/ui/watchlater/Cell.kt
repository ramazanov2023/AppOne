package com.example.appone.ui.watchlater

class Cell(
    val row: Int,
    val col: Int,
    val value: Int,
    var hide: Boolean = false,
    var wrong: Boolean = false,
    var wrong_number: Int = 0,
    var id: Int = 0
) {

}