package com.example.kmm_test.shared

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.Serializable
import io.ktor.http.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.*
import kotlinx.serialization.SerializationException

class PageGenerator {
    private val BASE_URL = "https://api.zaycev.fm/api/v1/channels"
    private val PAGE = "$BASE_URL/folk/latest"

    suspend fun getLatestSongsListOrException(): LatestSongResult =
        withContext(Dispatchers.Default) {

            val client = HttpClient {
                install(JsonFeature) {
                    serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                    })
                }
                expectSuccess = true
            }

            val response = client.use { httpClient ->
                try {
                    httpClient.request<LatestSongResponse> {
                        url(PAGE)
                        method = HttpMethod.Get
                    }
                } catch (e: Exception) {
                    when (e) {
                        is ClientRequestException -> {
                            ZLog.d(
                                "pageGenerator",
                                "ClientRequestException, e = ${e.response.status}"
                            )
                        }
                        is SerializationException -> {
                            ZLog.d(
                                "pageGenerator",
                                "SerializationException, e = ${e.message}"
                            )
                        }
                    }

                    e::class.simpleName?.let { ZLog.d("pageGenerator", it) }
                    return@withContext LatestSongResult.Exception("connect exception, code = ${e.message}")
                }
            }

            return@withContext LatestSongResult.Result(response.latest_fake.toMutableList())
        }
}


sealed class LatestSongResult {
    data class Result(val latestSongList: MutableList<LatestSong>) : LatestSongResult()
    data class Exception(val exception: String) : LatestSongResult()
}

//sealed class ApiCallResult<T> {
//    data class ApiCallSuccess<T>(val result: T?) : ApiCallResult<T>()
//    data class JSONReadException<T>(val unvalidatedField: String) : ApiCallResult<T>()
//    data class ServerResponseError<T>(val errorCode: Int, val fullMessage: String?) : ApiCallResult<T>()
//    data class ConnectionException<T>(val fullMessage: String?) : ApiCallResult<T>()
//}

@Serializable
data class LatestSong(
    val artist: String,
    val title: String
) : ValidateJSON

@Serializable
data class LatestSongResponse(
    val latest_fake: List<LatestSong>,
) : ValidateJSON

