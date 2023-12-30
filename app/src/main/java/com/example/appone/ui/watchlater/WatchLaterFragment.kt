package com.example.appone.ui.watchlater

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.appone.databinding.FragmentWatchLaterBinding

class WatchLaterFragment:Fragment(),SudokuBoardView.OnTouchListener {

    private val viewModel by viewModels<WatchLaterViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWatchLaterBinding.inflate(inflater)
        binding.sudokuBoard.registerOnTouchListener(this)

        viewModel.sudokuNumbers.numbersLiveData.observe(viewLifecycleOwner, Observer {
            binding.sudokuBoard.addSudokuNumbers(it)
        })

        viewModel.sudokuGame.selectedCellLiveData.observe(viewLifecycleOwner, Observer {
            binding.sudokuBoard.updateSelectedCell(it)
        })

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        return binding.root
    }

    override fun onTouchCell(selectRow: Int, selectCol: Int) {
        viewModel.sudokuGame.updateSelectedCell(selectRow,selectCol)
    }
}