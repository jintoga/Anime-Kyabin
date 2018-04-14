package com.jintoga.animekyabin

import android.content.Context
import com.jintoga.animekyabin.auth.AuthManager
import com.jintoga.animekyabin.auth.DefaultAuthManagerManager
import com.jintoga.animekyabin.auth.PreferenceHelper
import com.jintoga.animekyabin.repository.Repository
import com.jintoga.animekyabin.repository.RepositoryManager
import com.jintoga.animekyabin.repository.db.AppDatabase
import com.jintoga.animekyabin.repository.network.ClientApi
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