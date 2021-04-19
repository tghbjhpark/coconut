package com.hoya.ddory.coconut.cloud.request

import com.google.gson.annotations.SerializedName

data class AccountRequest(
    @SerializedName("apiKey") val apiKey: String,
    @SerializedName("secretKey") val secretKey: String,
    @SerializedName("order_currency") val orderCurrency: String,
    @SerializedName("payment_currency") val paymenyCurrency: String
)
