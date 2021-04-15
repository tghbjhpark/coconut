package com.hoya.ddory.coconut.cloud

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BithumbClient {

    private val retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.bithumb.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getPublicService() = retrofit.create(PublicApiInterface::class.java)
}
