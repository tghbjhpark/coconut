package com.hoya.ddory.coconut.cloud.response

import com.google.gson.annotations.SerializedName

data class OrderbookPublic(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: OrderbookPublicData
)

data class OrderbookPublicData(
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("order_currency") val orderCurrency: String,
    @SerializedName("payment_currency") val paymentCurrency: String,
    @SerializedName("bids") val bids: List<Transaction>,
    @SerializedName("asks") val asks: List<Transaction>
)

data class Transaction(
    @SerializedName("quantity") val quantity: String,
    @SerializedName("price") val price: String
)

