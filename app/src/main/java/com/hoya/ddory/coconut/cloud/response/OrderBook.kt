package com.hoya.ddory.coconut.cloud.response

import kotlinx.serialization.Serializable

@Serializable
data class OrderBook(
    val status: String,
    val data: OrderBookData
)

@Serializable
data class OrderBookData(
    val timestammp: String,
    val order_currency: String,
    val payment_currency: String,
    val bids: List<Transaction>,
    val asks: List<Transaction>
)

@Serializable
data class Transaction(
    val quantity: String,
    val price: String
)
