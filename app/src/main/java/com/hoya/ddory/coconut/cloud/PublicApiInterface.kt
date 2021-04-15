package com.hoya.ddory.coconut.cloud

import com.hoya.ddory.coconut.cloud.response.TickerPublic
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PublicApiInterface {
    @GET("public/ticker/{orderCurrency}_{paymentCurrency}")
    fun getTicker(
        @Path("orderCurrency") orderCurrency: String,
        @Path("paymentCurrency") paymentCurrency: String
    ) : Response<TickerPublic>
}
