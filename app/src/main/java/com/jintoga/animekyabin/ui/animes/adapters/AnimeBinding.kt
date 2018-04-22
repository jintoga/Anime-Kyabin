package com.jintoga.animekyabin.ui.animes.adapters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.databinding.BindingAdapter
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.widget.ImageView
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
import com.jintoga.animekyabin.repository.model.anime.Anime
import com.jintoga.animekyabin.ui.utils.AnimUtils
import com.jintoga.animekyabin.ui.utils.ObservableColorMatrix

object AnimeBinding {

    @BindingAdapter("app:loadImage")
    @JvmStatic
    fun loadImage(image: ImageView, anime: Anime) {
        val options = RequestOptions()
                .centerCrop()
                .downsample(DownsampleStrategy.DEFAULT)
                .placeholder(R.color.background_dark)
                .error(R.color.background_dark)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .priority(Priority.IMMEDIATE)

        Glide.with(image.context)
                .load(anime.imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(object : RequestListener<Drawable> {

                    override fun onResourceReady(resource: Drawable, model: Any,
                                                 target: Target<Drawable>,
                                                 dataSource: DataSource,
                                                 isFirstResource: Boolean): Boolean {
                        if (!anime.hasFadedIn) {
                            image.setHasTransientState(true)
                            val cm = ObservableColorMatrix()
                            val saturation = ObjectAnimator.ofFloat(
                                    cm, ObservableColorMatrix.SATURATION, 0f, 1f)
                            saturation.addUpdateListener({ _ ->
                                // just animating the color matrix does not invalidate the
                                // drawable so need this update listener.  Also have to create a
                                // new CMCF as the matrix is immutable :(
                                image.colorFilter = ColorMatrixColorFilter(cm)
                            })
                            saturation.duration = 2000L
                            saturation.interpolator = AnimUtils.getFastOutSlowInInterpolator(image.context)
                            saturation.addListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    image.clearColorFilter()
                                    image.setHasTransientState(false)
                                }
                            })
                            saturation.start()
                            anime.hasFadedIn = true
                        }
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any,
                                              target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        return false
                    }
                })
                .apply(options)
                .into(image)
    }
}