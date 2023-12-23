package com.example.appone

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("inWatchList")
fun initWatchedList(recycler: RecyclerView, watchedList: List<String>) {
    recycler.adapter
}