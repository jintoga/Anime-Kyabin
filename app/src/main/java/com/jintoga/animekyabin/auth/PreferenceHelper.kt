package com.jintoga.animekyabin.auth

import android.content.Context
import android.content.SharedPreferences
import com.jintoga.animekyabin.repository.model.auth.ClientCredentials
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PreferenceHelper @Inject constructor(context: Context) {

    companion object {
        private const val PREF_FILE_NAME = "AnimeListSharedPreference"
        private const val AUTH_TOKEN = "AUTH_TOKEN"
        private const val EXPIRES_TIME = "EXPIRES_TIME"
    }

    private var preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun getAuthToken(): String? = preferences.getString(AUTH_TOKEN, null)

    fun getExpireTime(): Long = preferences.getLong(EXPIRES_TIME, -1L)

    fun setClientCredentials(credentials: ClientCredentials) {
        preferences.edit().putLong(EXPIRES_TIME, credentials.expiresTime).apply()
        preferences.edit().putString(AUTH_TOKEN, credentials.accessToken).apply()
    }


}