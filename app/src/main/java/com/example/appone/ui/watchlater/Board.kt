package com.example.appone.ui.watchlater



const val HIDE_CELLS:Int = 10

class Board(val size:Int, val cells:List<Cell>) {
    fun getCell(row:Int, col:Int) = cells[row * size + col]
}