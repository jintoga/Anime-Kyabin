package com.jintoga.animekyabin.ui.animes

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.jintoga.animekyabin.repository.model.anime.Anime
import com.jintoga.animekyabin.ui.animes.adapters.AnimesAdapter

/**
 * Contains [AnimesAdapter]s for the [Anime] list.
 */
object AnimesBinding {
    @BindingAdapter("app:animes")
    @JvmStatic
    fun setAnimes(recyclerView: RecyclerView, animes: List<Anime>) {
        with(recyclerView.adapter as AnimesAdapter) {
            if (animes.isNotEmpty()) {
                setAnimes(animes)
            }
        }
    }

    @BindingAdapter("app:actualImageUri")
    @JvmStatic
    fun setActualImageUri(simpleDraweeView: SimpleDraweeView, imageUrl: String) {
        simpleDraweeView.setImageURI(imageUrl)
    }
}