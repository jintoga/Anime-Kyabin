package com.example.jintoga.animelist

import android.app.Application
import android.content.Context

class AnimeListApplication : Application() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    operator fun get(context: Context): Application =
            context.applicationContext as Application

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        appComponent.inject(this)
    }
}