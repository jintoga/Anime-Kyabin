package com.jintoga.animekyabin.helper

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import com.jintoga.animekyabin.AKApp


fun AppCompatActivity.applyToolbarMarginTop(toolbar: Toolbar) {
    (toolbar.layoutParams as ViewGroup.MarginLayoutParams).topMargin = getStatusBarHeight()
}

private fun getStatusBarHeight(): Int {
    val resources = AKApp.appComponent.context().resources
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}