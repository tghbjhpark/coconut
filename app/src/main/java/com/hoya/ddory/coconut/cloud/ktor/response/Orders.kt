package com.hoya.ddory.coconut.cloud.ktor.response

import kotlinx.serialization.Serializable

@Serializable
data class Orders(
    val status: String,
    val data: List<OrderData>
)

@Serializable
data class OrderData(
    val transaction_date: String,
    val type: String,
    val order_status: String,
    val order_currency: String,
    val payment_currency: String,
    val order_price: String,
    val order_qty: String,
    val contract: List<OrderContract>
)

@Serializable
data class OrderContract(
    val transaction_date: String,
    val price: String,
    val units: String,
    val fee_currency: String,
    val fee: String,
    val total: String
)
