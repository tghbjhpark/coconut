package com.hoya.ddory.coconut.cloud.ktor

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json

class BithumbClient {
    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
        install(Logging) {
            logger = object: Logger {
                override fun log(message: String) {
                    Log.i("KtorClient", message)
                }
            }
            level = LogLevel.ALL
        }
        install(HttpTimeout) {
            socketTimeoutMillis = 30_000
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 30_000
        }
    }
}
