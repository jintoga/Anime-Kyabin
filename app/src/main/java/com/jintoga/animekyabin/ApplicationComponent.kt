package com.jintoga.animekyabin

import android.content.Context
import com.jintoga.animekyabin.auth.AuthManager
import com.jintoga.animekyabin.auth.PreferenceHelper
import com.jintoga.animekyabin.repository.Repository
import com.jintoga.animekyabin.repository.db.AppDatabase
import com.jintoga.animekyabin.repository.network.ClientApi
import com.jintoga.animekyabin.repository.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        NetworkModule::class)
)
interface ApplicationComponent {

    fun inject(weatherApplication: AnimeListApplication)

    fun context(): Context

    fun clientApi(): ClientApi

    fun appDataBase(): AppDatabase

    fun repository(): Repository

    fun preferenceHelper(): PreferenceHelper

    fun authManager(): AuthManager
}