package com.jintoga.animekyabin.repository.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class XTokenInterceptor @Inject constructor() : Interceptor {

    private var authToken: String? = null

    fun setToken(token: String) {
        this.authToken = token
    }

    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain?.request()
        val builder = request?.newBuilder()
        builder?.addHeader("Content-Type", "application/x-www-form-urlencoded")
        val authToken = authToken
        if (authToken != null && authToken.isNotEmpty()) {
            builder?.addHeader("Authorization", "Bearer $authToken")
        }
        return chain?.proceed(builder!!.build())!!
    }
}