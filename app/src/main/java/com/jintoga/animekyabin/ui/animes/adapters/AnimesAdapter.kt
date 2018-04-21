package com.jintoga.animekyabin.ui.animes.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jintoga.animekyabin.R
import com.jintoga.animekyabin.databinding.ItemAnimeBinding
import com.jintoga.animekyabin.helper.inflater
import com.jintoga.animekyabin.repository.model.anime.Anime
import com.jintoga.animekyabin.ui.animes.AnimesViewModel


class AnimesAdapter(viewModel: AnimesViewModel)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_LOADING = 1
        private const val VIEW_TYPE_ANIME = 0
    }

    private val animes = ArrayList<Anime>()
    private var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemAnimeBinding
        return when (viewType) {
            VIEW_TYPE_LOADING -> {
                binding = DataBindingUtil.inflate(
                        parent.inflater,
                        R.layout.item_loading,
                        parent,
                        false)
                LoadingViewHolder(binding)
            }
            VIEW_TYPE_ANIME -> {
                binding = DataBindingUtil.inflate(
                        parent.inflater,
                        R.layout.item_anime,
                        parent,
                        false)
                AnimeViewHolder(binding)
            }
            else -> throw IllegalStateException()
        }
    }

    override fun getItemCount(): Int = if (isLoading) animes.size + 1 else animes.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AnimeViewHolder) {
            holder.bind(animes[position])
        }
    }

    fun setAnimes(animes: List<Anime>) {
        this.animes.clear()
        this.animes.addAll(animes)
        notifyDataSetChanged()
    }

    //ToDo: data binding?
    fun setIsLoading(isLoading: Boolean) {
        var changed = false
        if (this.isLoading != isLoading) {
            changed = true
        }
        this.isLoading = isLoading

        if (changed) when {
            isLoading -> notifyItemInserted(animes.size)
            else -> notifyItemRemoved(animes.size)
        }
    }
}