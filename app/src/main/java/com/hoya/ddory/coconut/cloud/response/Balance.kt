package com.hoya.ddory.coconut.cloud.response

import com.google.gson.annotations.SerializedName

data class Balance(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: BalanceData
)

data class BalanceData(
    @SerializedName("total_btc") val btc: String,
    @SerializedName("total_eth") val eth: String,
    @SerializedName("total_xrp") val xrp: String,
    @SerializedName("total_krw") val krw: String
)
