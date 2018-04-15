package com.jintoga.animekyabin.repository.network

import com.jintoga.animekyabin.AKApp
import okhttp3.Interceptor
import okhttp3.Response

class XTokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain?.request()
        val builder = request?.newBuilder()
        builder?.addHeader("Content-Type", "application/x-www-form-urlencoded")
        val authToken = AKApp.appComponent.authManager().getToken()
        if (authToken != null && authToken.isNotEmpty()) {
            builder?.addHeader("Authorization", "Bearer $authToken")
        }
        return chain?.proceed(builder!!.build())!!
    }
}