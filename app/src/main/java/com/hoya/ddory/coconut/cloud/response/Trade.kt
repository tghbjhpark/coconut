package com.hoya.ddory.coconut.cloud.response

import com.google.gson.annotations.SerializedName

data class Trade(
    @SerializedName("status") val status: String,
    @SerializedName("order_id") val id: String
)
