package com.hoya.ddory.coconut.cloud.response

import com.google.gson.annotations.SerializedName

data class TransactionPublic (
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: List<TransactionPublicData>
)

data class TransactionPublicData(
    @SerializedName("transaction_date") val date: String,
    @SerializedName("type") val type: String,
    @SerializedName("units_traded") val units: String,
    @SerializedName("price") val price: String,
    @SerializedName("total") val total: String
)
