package com.jintoga.animekyabin.ui.animes.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.jintoga.animekyabin.R
import com.jintoga.animekyabin.databinding.ItemAnimeBinding
import com.jintoga.animekyabin.repository.model.anime.Anime
import com.jintoga.animekyabin.ui.utils.AnimUtils.getFastOutSlowInInterpolator
import com.jintoga.animekyabin.ui.utils.ObservableColorMatrix
import kotlinx.android.synthetic.main.item_anime.view.*


class AnimeViewHolder(private val dataBinding: ItemAnimeBinding)
    : RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(anime: Anime) = with(itemView) {
        dataBinding.anime = anime
        dataBinding.executePendingBindings()
    }
}