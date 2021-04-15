package com.hoya.ddory.coconut.cloud.response

import com.google.gson.annotations.SerializedName

data class TickerPublic(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: TickerPublicData
)

data class TickerPublicData(
    @SerializedName("order_currency") val orderCurrency: String,
    @SerializedName("payment_currency") val paymentCurrency: String,
    @SerializedName("opening_price") val openingPrice: String,
    @SerializedName("closing_price") val closingPrice: String,
    @SerializedName("min_price") val minPrice: String,
    @SerializedName("max_price") val maxPrice: String,
    @SerializedName("average_price") val averagePrice: String,
    @SerializedName("units_traded") val unitsTraded: String,
    @SerializedName("date") val date: String
)