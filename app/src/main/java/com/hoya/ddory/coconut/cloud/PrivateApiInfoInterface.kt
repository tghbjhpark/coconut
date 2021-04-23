package com.hoya.ddory.coconut.cloud

import com.hoya.ddory.coconut.cloud.response.Account
import com.hoya.ddory.coconut.cloud.response.Balance
import com.hoya.ddory.coconut.cloud.response.Orders
import io.reactivex.Single
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface PrivateApiInfoInterface {
    @FormUrlEncoded
    @POST("/info/account")
    fun getAccount(
        @HeaderMap headers: HashMap<String, String>,
        @FieldMap param: HashMap<String, String>
    ): Single<Account>

    @FormUrlEncoded
    @POST("/info/balance")
    fun getBalance(
        @HeaderMap headers: HashMap<String, String>,
        @FieldMap param: HashMap<String, String>
    ): Single<Balance>

    @FormUrlEncoded
    @POST("/info/orders")
    fun getOrders(
        @HeaderMap headers: HashMap<String, String>,
        @FieldMap param: HashMap<String, String>
    ): Single<Orders>
}
