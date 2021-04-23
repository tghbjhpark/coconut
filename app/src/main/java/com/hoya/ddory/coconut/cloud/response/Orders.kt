package com.hoya.ddory.coconut.cloud.response

import com.google.gson.annotations.SerializedName

data class Orders(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: List<OrderData>
)

data class OrderData(
    @SerializedName("order_currency") val orderCurrency: String,
    @SerializedName("payment_currency") val paymentCurrency: String,
    @SerializedName("order_id") val id: String,
    @SerializedName("order_date") val date: String,
    @SerializedName("type") val type: String,
    @SerializedName("units") val units: String,
    @SerializedName("units_remaining") val unitsRemaining: String,
    @SerializedName("price") val price: String
)
