package com.hoya.ddory.coconut.cloud.response

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val status: String,
    val data: OrderData
)

@Serializable
data class OrderData(
    val order_date: String,
    val type: String,
    val order_status: String,
    val order_currency: String,
    val payment_currency: String,
    val watch_price: String,
    val order_price: String,
    val order_qty: String,
    val cancel_date: String,
    val cancel_type: String,
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
