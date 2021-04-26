package com.hoya.ddory.coconut.cloud

import com.hoya.ddory.coconut.cloud.response.Trade
import io.reactivex.Single
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface PrivateApiTradeInterface {
    @FormUrlEncoded
    @POST("/trade/market_buy")
    fun buyMarketPrice(
        @HeaderMap headers: HashMap<String, String>,
        @FieldMap param: HashMap<String, String>
    ): Single<Trade>

    @FormUrlEncoded
    @POST("/trade/market_sell")
    fun sellMarketPrice(
        @HeaderMap headers: HashMap<String, String>,
        @FieldMap param: HashMap<String, String>
    ): Single<Trade>
}
