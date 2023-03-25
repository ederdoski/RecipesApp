package com.salmo23.bibliaydevocional.core.network.model

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("code") val code: Int,
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("errorDescription") val errorDescription: String,
    @SerializedName("data") val data: T
)
