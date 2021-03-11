package com.example.kmm_test.shared

import com.example.kmm_test.shared.di.EngineSDK
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.suspendCoroutine

interface ILatestSongRepository {
    suspend fun getLatestSongsListOrException(): LatestSongResult
}

class LatestSongRepository(private val logger: ZLog) : ApiRepository(), ILatestSongRepository {
    private val baseURL = "https://api.zaycev.fm/api/v1/channels"
    private val latestSongsURL = "$baseURL/folk/latest"

    val job = Job()

    override suspend fun getLatestSongsListOrException(): LatestSongResult =
        withContext(Dispatchers.Default) {
        //with(CoroutineScope(coroutineContext)) {
            val response: LatestSongResponse = try {
                getAnythingOrException(latestSongsURL)
            } catch (e: Exception) {
                // Можно обработать каждое исключение отдельно
                // Не используй EngineSDK.di внутри коррутин - на Swift есть проблема с этим
                when (e) {
                    is ClientRequestException -> {
                        logger.d(
                            "LatestSongRepository",
                            "ClientRequestException, e = ${e.message}"
                        )
                    }
                    is SerializationException -> {
                        logger.d(
                            "LatestSongRepository",
                            "SerializationException, e = ${e.message}"
                        )
                    }
                }
                return@withContext LatestSongResult.Exception("exception, code = ${e.message}")
            }
            return@withContext LatestSongResult.Result(response.latest.toMutableList())
        }
}