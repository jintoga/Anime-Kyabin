package com.jintoga.animekyabin.injection.modules

import android.content.Context
import com.jintoga.animekyabin.AKApp
import com.jintoga.animekyabin.auth.AuthManager
import com.jintoga.animekyabin.auth.DefaultAuthManagerManager
import com.jintoga.animekyabin.auth.PreferenceHelper
import com.jintoga.animekyabin.repository.Repository
import com.jintoga.animekyabin.repository.RepositoryManager
import com.jintoga.animekyabin.repository.db.AppDatabase
import com.jintoga.animekyabin.repository.network.ClientApi
import com.jintoga.animekyabin.repository.network.XTokenInterceptor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: AKApp) {

    @Provides
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideApplication(): AKApp = application

    @Provides
    @Singleton
    fun provideAppDataBase(): AppDatabase = AppDatabase.getInstance(application)

    @Provides
    @Singleton
    fun provideRepository(clientApi: ClientApi, appDatabase: AppDatabase)
            : Repository = RepositoryManager(clientApi, appDatabase)

    @Provides
    @Singleton
    fun provideAuthManager(clientApi: ClientApi,
                           preferenceHelper: PreferenceHelper,
                           xTokenInterceptor: XTokenInterceptor)
            : AuthManager = DefaultAuthManagerManager(clientApi, preferenceHelper, xTokenInterceptor)
}