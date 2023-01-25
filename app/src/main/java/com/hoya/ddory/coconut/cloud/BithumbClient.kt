package com.hoya.ddory.coconut.cloud

import android.content.Context
import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

class BithumbClient(context: Context) {
    private val log: com.hoya.ddory.coconut.model.Logger by lazy {
        com.hoya.ddory.coconut.model.Logger(context)
    }

    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.i("KtorClient", message)
                    message.takeIf {
                        it.startsWith("REQUEST:") ||
                                it.startsWith("RESPONSE:") ||
                                it.startsWith("FROM:") ||
                                it.startsWith("{") ||
                                it.startsWith("endpoint")
                    }?.also {
                        log.http("BithumbClient", it)
                    }
                }
            }
            level = LogLevel.BODY
        }
        install(HttpTimeout) {
            socketTimeoutMillis = 30_000
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 30_000
        }
    }
}
