package com.hoya.ddory.coconut.cloud

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object BithumbClient {

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.bithumb.com")
            .client(OkHttpClient.Builder().addInterceptor(createInteceptor()).build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private fun createInteceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun getPublicService() = retrofit.create(PublicApiInterface::class.java)

    fun getPrivateService() = retrofit.create(PrivateApiInfoInterface::class.java)

    fun getTradeService() = retrofit.create(PrivateApiTradeInterface::class.java)
}
