package com.jintoga.animekyabin.ui.animes.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.animation.Animation
import com.jintoga.animekyabin.R
import com.jintoga.animekyabin.databinding.ItemAnimeBinding
import com.jintoga.animekyabin.helper.inflater
import com.jintoga.animekyabin.repository.model.anime.Anime
import com.jintoga.animekyabin.ui.animes.AnimesViewModel
import com.jintoga.animekyabin.ui.widget.GridRecyclerView


class AnimesAdapter(viewModel: AnimesViewModel, private val animesRecyclerView: GridRecyclerView)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Animation.AnimationListener {

    override fun onAnimationRepeat(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) {
    }

    override fun onAnimationStart(animation: Animation?) {
    }

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

    fun setAnimes(data: List<Anime>) {
        if (this.animes.isNotEmpty()) {
            //There are probably already loaded items with FadedIn animation(hasFadedIn),
            // so set hasFadedIn back to "updated" items to prevent animation from repeating
            persistFadeState(data)
            this.animes.clear()
            notifyDataSetChanged()
        }
        this.animes.addAll(data)
        notifyItemRangeInserted(0, itemCount)
    }

    private fun persistFadeState(data: List<Anime>) {
        this.animes.forEach loop@{ currentItem ->
            data.forEach { newItem ->
                if (currentItem.id == newItem.id) {
                    newItem.hasFadedIn = currentItem.hasFadedIn
                    return@loop
                }
            }
        }
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