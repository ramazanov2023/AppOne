package com.example.appone.ui.watchlater

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WatchLaterViewModel : ViewModel() {
    val sudokuGame = SudokuGame()
    val sudokuNumbers = SudokuNumbersGenerator()

    private val _number = MutableLiveData<Int>()
    val number: LiveData<Int>
        get() = _number

    init {
        sudokuNumbers.getShuffleNumbersList()
    }

    fun generateNumbers() {
        sudokuNumbers.getShuffleNumbersList()
        Log.e("asas", "1 generateNumbers")
    }

    fun insertNumber(value: Int) {
        _number.value = value
        Log.e("insins", "1  -  $value")
    }

}