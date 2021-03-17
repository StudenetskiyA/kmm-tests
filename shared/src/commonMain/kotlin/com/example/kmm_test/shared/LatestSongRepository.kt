package com.example.kmm_test.shared

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException

interface ILatestSongRepository {
    suspend fun getLatestSongsListOrException(): LatestSongResult
}

class LatestSongRepository : ApiRepository(), ILatestSongRepository {
    companion object {

    }
    private val baseURL = "https://api.zaycev.fm/api/v1/channels"
    private val latestSongsURL = "$baseURL/folk/latest"

    override suspend fun getLatestSongsListOrException(): LatestSongResult =
        withContext(Dispatchers.Default) {
            val response: LatestSongResponse = try {
                getAnythingOrException(latestSongsURL)
            } catch (e: Exception) {
                // Можно обработать каждое исключение отдельно
                // Не используй EngineSDK.di внутри коррутин - EngineSDK.di ThreadLocal
                // Хотя на Андроид работает))
//                when (e) {
//                    is ClientRequestException -> {
//                        logger.d(
//                            "LatestSongRepository",
//                            "ClientRequestException, e = ${e.message}"
//                        )
//                    }
//                    is SerializationException -> {
//                        logger.d(
//                            "LatestSongRepository",
//                            "SerializationException, e = ${e.message}"
//                        )
//                    }
//                }
                return@withContext LatestSongResult.Exception("exception, code = ${e.message}")
            }
            return@withContext LatestSongResult.Result(response.latest.toMutableList())
        }
}