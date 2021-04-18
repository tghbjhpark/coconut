package com.hoya.ddory.coconut.cloud.response

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: String
)

data class AccountData(
    @SerializedName("created") val created: String,
    @SerializedName("account_id") val id: String,
    @SerializedName("order_currency") val orderCurrency: String,
    @SerializedName("payment_currency") val paymentCurrency: String,
    @SerializedName("trade_fee") val fee: String,
    @SerializedName("balance") val balance: String
)
