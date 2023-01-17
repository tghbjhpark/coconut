package com.hoya.ddory.coconut.cloud.response

import kotlinx.serialization.Serializable

@Serializable
data class TransactionHistory(
    val status: String,
    val data: List<TransactionHistoryData>
)

@Serializable
data class TransactionHistoryData(
    val transaction_date: String,
    val type: String,
    val units_traded: String,
    val price: String,
    val total: String
)
