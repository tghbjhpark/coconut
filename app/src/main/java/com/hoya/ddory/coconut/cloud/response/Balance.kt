package com.hoya.ddory.coconut.cloud.response

import kotlinx.serialization.Serializable

@Serializable
data class Balance(
    val status: String,
    val data: BalanceData
)

@Serializable
data class BalanceData(
    val btc: String,
    val eth: String,
    val xrp: String,
    val krw: String
)
