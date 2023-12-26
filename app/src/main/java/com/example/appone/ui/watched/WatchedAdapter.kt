package com.example.appone.ui.watched

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appone.data.local.WatchedAnime
import com.example.appone.databinding.ItemWatchedAnimeBinding

class WatchedAdapter: ListAdapter<WatchedAnime, WatchedAdapter.Holder>(Diff) {
    class Holder(private val binding:ItemWatchedAnimeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(anime:WatchedAnime){
            binding.anime = anime
            binding.executePendingBindings()
        }
    }

    companion object Diff:DiffUtil.ItemCallback<WatchedAnime>(){
        override fun areItemsTheSame(oldItem: WatchedAnime, newItem: WatchedAnime): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WatchedAnime, newItem: WatchedAnime): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemWatchedAnimeBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }


}
