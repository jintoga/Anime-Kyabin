package com.jintoga.animekyabin.repository.model.auth

import com.google.gson.annotations.SerializedName

data class ClientCredentials(
        @SerializedName("access_token") val accessToken: String,
        @SerializedName("expires") val expiresTime: Long
)