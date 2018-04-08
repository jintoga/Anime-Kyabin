package com.example.jintoga.animelist

import android.content.Context
import com.example.jintoga.animelist.auth.AuthManager
import com.example.jintoga.animelist.auth.DefaultAuthManagerManager
import com.example.jintoga.animelist.auth.PreferenceHelper
import com.example.jintoga.animelist.repository.Repository
import com.example.jintoga.animelist.repository.RepositoryManager
import com.example.jintoga.animelist.repository.db.AppDatabase
import com.example.jintoga.animelist.repository.network.ClientApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AnimeListApplication) {

    @Provides
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideApplication(): AnimeListApplication = application

    @Provides
    @Singleton
    fun provideAppDataBase(): AppDatabase = AppDatabase.getInstance(application)

    @Provides
    @Singleton
    fun provideRepository(clientApi: ClientApi, appDatabase: AppDatabase)
            : Repository = RepositoryManager(clientApi, appDatabase)

    @Provides
    @Singleton
    fun provideAuthManager(clientApi: ClientApi, preferenceHelper: PreferenceHelper)
            : AuthManager = DefaultAuthManagerManager(clientApi, preferenceHelper)
}