package com.example.appone.ui.watchlater

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SudokuGame {
    private val _selectedCellLiveData = MutableLiveData<Pair<Int, Int>>()

    private var selectedRow = -1
    private var selectedCol = -1

    val selectedCellLiveData: LiveData<Pair<Int, Int>>
        get() = _selectedCellLiveData

    fun updateSelectedCell(r:Int,c:Int) {
        selectedRow = r
        selectedCol = c
        _selectedCellLiveData.value = Pair(r,c)
    }
}