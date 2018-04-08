package com.example.jintoga.animelist.repository.model.auth

import com.google.gson.annotations.SerializedName

class ClientCredentialsRequest(
        @SerializedName("grant_type") val grantType: String,
        @SerializedName("client_id") val clientId: String,
        @SerializedName("client_secret") val clientSecret: String
)