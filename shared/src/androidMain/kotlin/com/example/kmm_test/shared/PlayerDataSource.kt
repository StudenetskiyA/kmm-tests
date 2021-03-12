package com.example.kmm_test.shared

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import com.google.android.exoplayer2.*
import java.io.IOException


actual class PlayerDataSource {
    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context
    }

    actual fun playURL(url: String) {

        val player = SimpleExoPlayer.Builder(context).build()
        val mediaItem: MediaItem = MediaItem.fromUri("http://zaycevfm-hls.cdnvideo.ru/zaycevfm-shout/ZaycevFM_folk_64_aac.stream/playlist.m3u8")
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }
}