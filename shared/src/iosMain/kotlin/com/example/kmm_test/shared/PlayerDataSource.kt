package com.example.kmm_test.shared

import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.play
import platform.Foundation.NSURL

actual class PlayerDataSource {
    var playerItem: AVPlayerItem? = null
    var player: AVPlayer? = null

    actual fun playURL(url: String) {
        try {
            // TODO Current not play HLS like "http://zaycevfm-hls.cdnvideo.ru/zaycevfm-shout/ZaycevFM_folk_64_aac.stream/playlist.m3u8"
            val nsURL = NSURL(string = url)
            playerItem = AVPlayerItem(uRL = nsURL)
            player=AVPlayer(playerItem = playerItem)
            player!!.play()
        } catch (e: Exception) {
            println("skyfolk-kmm : player exception = ${e.message}")
        }
    }
}