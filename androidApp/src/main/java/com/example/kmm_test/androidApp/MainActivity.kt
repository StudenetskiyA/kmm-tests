package com.example.kmm_test.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.kmm_test.shared.di.*
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class MainActivity : AppCompatActivity() {
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EngineSDK.init(Configuration(PlatformType.Android("1.0",1), isDebug = true))
        EngineSDK.settingsDataSource.init(applicationContext)
        EngineSDK.playerDataSource.init(applicationContext)

        val tv: TextView = findViewById(R.id.text_view)

        viewModel.state.observe(this, { state ->
            tv.text = state
        })

        val button: Button = findViewById(R.id.button)
        val buttonSetConfig: Button = findViewById(R.id.button_set_config)
        val buttonGetConfig: Button = findViewById(R.id.button_get_config)
        val buttonPlay: Button = findViewById(R.id.button_play)

        button.setOnClickListener {
            viewModel.test3()
        }

        buttonGetConfig.setOnClickListener {
            tv.text = EngineSDK.settingsInteractor.getTestSettings()
        }

        buttonSetConfig.setOnClickListener {
            EngineSDK.settingsInteractor.setTestSettings("new value")
        }

        buttonPlay.setOnClickListener {
            EngineSDK.playerInteractor.playURL("http://zaycevfm-hls.cdnvideo.ru/zaycevfm-shout/ZaycevFM_folk_64_aac.stream/playlist.m3u8")
        }


    }
}
