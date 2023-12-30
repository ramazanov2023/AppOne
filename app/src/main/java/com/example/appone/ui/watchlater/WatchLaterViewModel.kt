package com.example.appone.ui.watchlater

import android.util.Log
import androidx.lifecycle.ViewModel

class WatchLaterViewModel:ViewModel() {
    val sudokuGame = SudokuGame()
    val sudokuNumbers = SudokuNumbersGenerator()

    init {
        sudokuNumbers.getShuffleNumbersList()
    }

    fun generateNumbers(){
        sudokuNumbers.getShuffleNumbersList()
        Log.e("asas","1 generateNumbers")
    }

    fun insertNumber(value:Int){

    }

}