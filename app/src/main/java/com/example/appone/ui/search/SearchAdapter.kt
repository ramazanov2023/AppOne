package com.example.appone.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appone.data.local.SearchAnime
import com.example.appone.databinding.ItemSearchAnimeBinding

class SearchAdapter: ListAdapter<SearchAnime, SearchAdapter.Holder>(Diff) {
    class Holder(private val binding:ItemSearchAnimeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(anime:SearchAnime){
            binding.anime = anime
            binding.executePendingBindings()
        }
    }

    companion object Diff:DiffUtil.ItemCallback<SearchAnime>(){
        override fun areItemsTheSame(oldItem: SearchAnime, newItem: SearchAnime): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SearchAnime, newItem: SearchAnime): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ItemSearchAnimeBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }


}
