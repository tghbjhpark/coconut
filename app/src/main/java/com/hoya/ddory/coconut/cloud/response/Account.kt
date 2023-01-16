package com.hoya.ddory.coconut.cloud.response

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val status: String,
    val data: AccountData
)

@Serializable
data class AccountData(
    val created: String,
    val account_id: String,
    val order_currency: String,
    val payment_currency: String,
    val trade_fee: String,
    val balance: String
)
