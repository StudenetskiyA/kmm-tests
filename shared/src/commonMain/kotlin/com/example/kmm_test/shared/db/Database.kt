package com.example.kmm_test.shared.db

import com.example.kmm_test.shared.LatestSong
import com.example.kmm_test.shared.LatestSongDBEntity


class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllSongs()
        }
    }

    internal fun getAllSongs(): List<LatestSongDBEntity> {
        return dbQuery.selectAllSongs(::mapLatestSong).executeAsList()
    }

    private fun getSongById(id: String): LatestSong? {
        return dbQuery.selectAllSongs(::mapLatestSong).executeAsList().find { it.id == id}?.toLatestSong()
    }

    private fun mapLatestSong(
        id: String,
        artist: String,
        title: String,
        date: Long): LatestSongDBEntity {
        return LatestSongDBEntity(
            id = id,
            artist = artist,
            title = title,
            date = date)
    }

    internal fun insertSong(song: LatestSongDBEntity) {
            dbQuery.insertSong(
                id = song.id,
                artist = song.artist,
                title = song.title,
                date = song.date
            )
    }
}