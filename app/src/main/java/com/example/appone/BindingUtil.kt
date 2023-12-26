package com.example.appone

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appone.data.local.SearchAnime
import com.example.appone.data.local.WatchedAnime
import com.example.appone.ui.search.SearchAdapter
import com.example.appone.ui.watched.WatchedAdapter

@BindingAdapter("inWatchList")
fun initWatchedList(recycler: RecyclerView, watchedList: List<WatchedAnime>?) {
    watchedList?.let {
        (recycler.adapter as WatchedAdapter).submitList(watchedList)
    }
}

@BindingAdapter("inSearchList")
fun initSearchList(recycler: RecyclerView, searchList: List<SearchAnime>?) {
    searchList?.let {
        (recycler.adapter as SearchAdapter).submitList(searchList)
    }
}

@BindingAdapter("inAnimePreview")
fun initAnimePreview(resource:ImageView, image: String?) {
    image?.let {
        Glide
            .with(resource.context)
            .load(image)
            .fitCenter()
            .into(resource)
    }
}