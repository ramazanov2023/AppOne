package com.example.appone.ui.watched

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appone.data.local.LocalAnime
import com.example.appone.databinding.ItemAnimeBinding

class WatchedAdapter: ListAdapter<LocalAnime, WatchedAdapter.Holder>(Diff) {
    class Holder(private val binding:ItemAnimeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(anime:LocalAnime){
            binding.anime = anime
            binding.executePendingBindings()
        }
    }

    companion object Diff:DiffUtil.ItemCallback<LocalAnime>(){
        override fun areItemsTheSame(oldItem: LocalAnime, newItem: LocalAnime): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LocalAnime, newItem: LocalAnime): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemAnimeBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }


}
