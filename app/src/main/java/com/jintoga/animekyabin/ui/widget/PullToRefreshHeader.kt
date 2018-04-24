package com.jintoga.animekyabin.ui.widget

import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrUIHandler
import `in`.srain.cube.views.ptr.indicator.PtrIndicator
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.Log
import com.jintoga.animekyabin.R

class PullToRefreshHeader : ConstraintLayout, PtrUIHandler {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        inflate(context, R.layout.item_loading, this)

    }

    override fun onUIRefreshComplete(frame: PtrFrameLayout?) {
        Log.d("onUIRefreshComplete", "onUIRefreshComplete")
    }

    override fun onUIPositionChange(frame: PtrFrameLayout?, isUnderTouch: Boolean, status: Byte, ptrIndicator: PtrIndicator?) {
        Log.d("onUIPositionChange", "isUnderTouch: $isUnderTouch status: $status ")
    }

    override fun onUIRefreshBegin(frame: PtrFrameLayout?) {
        Log.d("onUIRefreshBegin", "onUIRefreshBegin")
    }

    override fun onUIRefreshPrepare(frame: PtrFrameLayout?) {
        Log.d("onUIRefreshPrepare", "onUIRefreshPrepare")
    }

    override fun onUIReset(frame: PtrFrameLayout?) {
        Log.d("onUIReset", "onUIReset")
    }
}