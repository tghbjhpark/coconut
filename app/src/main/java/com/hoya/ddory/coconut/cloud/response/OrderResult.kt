package com.hoya.ddory.coconut.cloud.response

import kotlinx.serialization.Serializable

@Serializable
data class OrderResult(
    val status: String,
    val order_id: String
)
