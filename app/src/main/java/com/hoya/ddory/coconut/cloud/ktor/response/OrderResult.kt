package com.hoya.ddory.coconut.cloud.ktor.response

import kotlinx.serialization.Serializable

@Serializable
data class OrderResult(
    val status: String,
    val order_id: String
)
