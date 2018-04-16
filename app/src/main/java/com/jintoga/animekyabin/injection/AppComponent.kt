package com.jintoga.animekyabin.injection

import android.content.Context
import com.jintoga.animekyabin.auth.AuthManager
import com.jintoga.animekyabin.auth.PreferenceHelper
import com.jintoga.animekyabin.injection.modules.AppModule
import com.jintoga.animekyabin.injection.modules.NetworkModule
import com.jintoga.animekyabin.injection.modules.ViewModelModule
import com.jintoga.animekyabin.repository.Repository
import com.jintoga.animekyabin.repository.db.AppDatabase
import com.jintoga.animekyabin.repository.network.ClientApi
import com.jintoga.animekyabin.repository.network.XTokenInterceptor
import com.jintoga.animekyabin.ui.animes.AnimesActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class), (ViewModelModule::class)])
interface AppComponent {

    fun inject(activity: AnimesActivity)

    fun context(): Context

    fun clientApi(): ClientApi

    fun appDataBase(): AppDatabase

    fun repository(): Repository

    fun preferenceHelper(): PreferenceHelper

    fun authManager(): AuthManager

    fun tokenInterceptor(): XTokenInterceptor
}