package com.jintoga.animekyabin

import android.app.Application
import android.content.Context
import com.jintoga.animekyabin.injection.AppComponent
import com.jintoga.animekyabin.injection.DaggerAppComponent
import com.jintoga.animekyabin.injection.modules.AppModule

class AKApp : Application() {

    lateinit var appComponent: AppComponent private set

    operator fun get(context: Context): Application =
            context.applicationContext as Application

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}