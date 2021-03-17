package com.example.kmm_test.shared

import com.example.kmm_test.shared.di.EngineSDK
import com.example.kmm_test.shared.di.logger
import kotlinx.serialization.Serializable

interface ILatestSongInteractor {
    suspend fun getLatestSongsListOrException(): LatestSongResult
    fun getTest(): String
}

class LatestSongInteractor(private val latestSongRepository: ILatestSongRepository) : ILatestSongInteractor {
    override suspend fun getLatestSongsListOrException(): LatestSongResult = latestSongRepository.getLatestSongsListOrException()
    override fun getTest(): String {
        EngineSDK.logger.d(
            "pageGenerator",
            "ClientRequestException"
        )
        return "Test"
    }
}


sealed class LatestSongResult {
    //Do not use MutableList - swift not work with it
    data class Result(val latestSongList: List<LatestSong>) : LatestSongResult()
    data class Exception(val exception: String) : LatestSongResult()
}

@Serializable
data class LatestSong(
    val artist: String,
    val title: String
)

@Serializable
data class LatestSongResponse(
    val latest: List<LatestSong>,
)

@Serializable
data class LatestSongDBEntity(
    val id: String,
    val artist: String,
    val title: String,
    val date: Long
) {
    fun toLatestSong(): LatestSong {
        return LatestSong(this.artist, this.title)
    }
}
