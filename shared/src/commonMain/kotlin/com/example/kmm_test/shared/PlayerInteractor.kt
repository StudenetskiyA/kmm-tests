package com.example.kmm_test.shared

interface IPLayerInteractor {
    fun playURL (url: String)
}

class PlayerInteractor(private val playerDataSource: PlayerDataSource): IPLayerInteractor {
    override fun playURL(url: String) {
        playerDataSource.playURL(url)
    }
}