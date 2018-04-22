package com.jintoga.animekyabin.helper

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jintoga.animekyabin.AKApp
import com.jintoga.animekyabin.injection.AppComponent

val Activity.app: AKApp get() = application as AKApp

val AppCompatActivity.appComponent: AppComponent get() = app.appComponent

fun View.applyMarginTop() {
    (this.layoutParams as ViewGroup.MarginLayoutParams).topMargin = context.getStatusBarHeight()
}

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Context.getNavigationBarHeight(): Int {
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId > 0) {
        resources.getDimensionPixelSize(resourceId)
    } else 0
}


inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}

val ViewGroup.inflater: LayoutInflater get() = LayoutInflater.from(context)

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
        inflater.inflate(layoutId, this, attachToRoot)