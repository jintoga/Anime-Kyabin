package com.jintoga.animekyabin.ui.animes.adapters

import android.support.v7.widget.RecyclerView
import com.jintoga.animekyabin.databinding.ItemAnimeBinding
import com.jintoga.animekyabin.repository.model.anime.Anime

class AnimeViewHolder(private val dataBinding: ItemAnimeBinding)
    : RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(anime: Anime) {
        dataBinding.anime = anime
        dataBinding.executePendingBindings()
    }
}