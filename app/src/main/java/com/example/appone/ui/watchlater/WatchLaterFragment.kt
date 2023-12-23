package com.example.appone.ui.watchlater

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appone.databinding.FragmentWatchLaterBinding

class WatchLaterFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWatchLaterBinding.inflate(inflater)






        return binding.root
    }
}