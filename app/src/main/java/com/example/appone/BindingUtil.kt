package com.example.appone

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appone.data.local.LocalAnime
import com.example.appone.ui.watched.WatchedAdapter

@BindingAdapter("inWatchList")
fun initWatchedList(recycler: RecyclerView, watchedList: List<LocalAnime>) {
    watchedList?.let {
        (recycler.adapter as WatchedAdapter).submitList(watchedList)
    }
}