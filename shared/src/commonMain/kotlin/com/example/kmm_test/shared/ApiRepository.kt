package com.example.kmm_test.shared

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlin.native.concurrent.ThreadLocal

open class ApiRepository {
    suspend inline fun <reified T> getAnythingOrException(url: String): T {
        val httpClient = HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                })
            }
            expectSuccess = true
        }
        val result: T = httpClient.get(url)
        httpClient.close()
        return result
    }
}