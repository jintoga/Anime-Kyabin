package com.jintoga.animekyabin

import android.app.Application
import android.content.Context
import com.facebook.cache.disk.DiskCacheConfig
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig
import com.facebook.imagepipeline.listener.RequestListener
import com.facebook.imagepipeline.listener.RequestLoggingListener
import com.jintoga.animekyabin.injection.AppComponent
import com.jintoga.animekyabin.injection.DaggerAppComponent
import com.jintoga.animekyabin.injection.modules.AppModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*

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

        initFresco()
    }

    private fun initFresco() {
        val requestListeners = HashSet<RequestLoggingListener>()
        requestListeners.add(RequestLoggingListener())
        val okHttpClient = OkHttpClient()
                .newBuilder()
                .addInterceptor(HttpLoggingInterceptor())
                .build()

        val cache = DiskCacheConfig.newBuilder(this).build()
        val config = OkHttpImagePipelineConfigFactory.newBuilder(applicationContext, okHttpClient)
                .setMainDiskCacheConfig(cache)
                .setProgressiveJpegConfig(SimpleProgressiveJpegConfig())
                .setDownsampleEnabled(true)
                .setRequestListeners(requestListeners as Set<RequestListener>)
                .build()

        Fresco.initialize(applicationContext, config)
    }
}