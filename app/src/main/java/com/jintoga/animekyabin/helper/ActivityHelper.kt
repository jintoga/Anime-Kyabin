package com.jintoga.animekyabin.helper

import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import com.jintoga.animekyabin.AKApp
import com.jintoga.animekyabin.injection.AppComponent

val Activity.app: AKApp get() = application as AKApp

val AppCompatActivity.appComponent: AppComponent get() = app.appComponent

fun Toolbar.applyMarginTop() {
    (this.layoutParams as ViewGroup.MarginLayoutParams).topMargin = this.context.getStatusBarHeight()
}

private fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}


inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}