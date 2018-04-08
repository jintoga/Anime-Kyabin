package com.example.jintoga.animelist

import android.content.Context
import com.example.jintoga.animelist.auth.AuthManager
import com.example.jintoga.animelist.auth.PreferenceHelper
import com.example.jintoga.animelist.repository.Repository
import com.example.jintoga.animelist.repository.db.AppDatabase
import com.example.jintoga.animelist.repository.network.ClientApi
import com.example.jintoga.animelist.repository.network.NetworkModule
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