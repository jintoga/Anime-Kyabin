package com.jintoga.animekyabin

import android.app.Application
import android.content.Context

class AKApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    operator fun get(context: Context): Application =
            context.applicationContext as Application

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        appComponent.inject(this)
    }
}