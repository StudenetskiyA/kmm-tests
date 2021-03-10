package com.example.kmm_test.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kmm_test.shared.Greeting
import android.widget.TextView
import com.example.kmm_test.shared.di.Configuration
import com.example.kmm_test.shared.di.EngineSDK
import com.example.kmm_test.shared.di.PlatformType
import com.example.kmm_test.shared.di.pageGenerator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EngineSDK.init(Configuration(PlatformType.Android("1.0",1), isDebug = true))

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = EngineSDK.pageGenerator.getGreeting()
    }
}
