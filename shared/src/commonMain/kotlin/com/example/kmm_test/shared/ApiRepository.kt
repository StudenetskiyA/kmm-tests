package com.example.kmm_test.shared

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

open class ApiRepository {
    lateinit var httpClient: HttpClient

    suspend inline fun <reified T> getAnythingOrException(url: String): T {
        initClient()
        val result: T = httpClient.get(url)
        httpClient.close()
        return result
    }

    fun initClient() {
        httpClient = HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                })
            }
            expectSuccess = true
        }
    }
}