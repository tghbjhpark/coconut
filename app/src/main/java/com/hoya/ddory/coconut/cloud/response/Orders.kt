package com.hoya.ddory.coconut.cloud.response

import com.google.gson.annotations.SerializedName

data class Orders(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: List<OrderData>
)

data class OrderData(
    @SerializedName("transaction_date") val date: String,
    @SerializedName("type") val type: String,
    @SerializedName("order_status") val status: String,
    @SerializedName("order_currency") val orderCurrency: String,
    @SerializedName("payment_currency") val paymentCurrency: String,
    @SerializedName("order_price") val price: String,
    @SerializedName("order_qty") val quantity: String,
    @SerializedName("contract") val contract: List<OrderContract>
)

data class OrderContract(
    @SerializedName("transaction_date") val date: String,
    @SerializedName("price") val price: String,
    @SerializedName("units") val units: String,
    @SerializedName("fee_currency") val feeCurrency: String,
    @SerializedName("fee") val fee: String,
    @SerializedName("total") val total: String
)
