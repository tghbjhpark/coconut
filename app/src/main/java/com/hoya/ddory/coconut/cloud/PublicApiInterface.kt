package com.hoya.ddory.coconut.cloud

import com.hoya.ddory.coconut.cloud.response.OrderbookPublic
import com.hoya.ddory.coconut.cloud.response.TickerPublic
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicApiInterface {
    @GET("public/ticker/{orderCurrency}_{paymentCurrency}")
    fun getTicker(
        @Path("orderCurrency") orderCurrency: String,
        @Path("paymentCurrency") paymentCurrency: String
    ) : Single<TickerPublic>

    @GET("public/orderbook/{orderCurrency}_{paymentCurrency}")
    fun getOrderbook(
        @Path("orderCurrency") orderCurrency: String,
        @Path("paymentCurrency") paymentCurrency: String
    ) : Single<OrderbookPublic>
}
