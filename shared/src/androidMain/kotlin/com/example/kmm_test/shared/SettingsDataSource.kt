package com.example.kmm_test.shared

import android.content.Context
import android.content.SharedPreferences

actual class SettingsDataSource {
    companion object {
        const val SETTINGS_PREFERENCES = "settings"
        const val TEST_SETTINGS = "test_settings"
    }

    lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(SETTINGS_PREFERENCES, Context.MODE_PRIVATE)
    }

    actual fun getTestSettings(): String {
        return sharedPreferences.getString(TEST_SETTINGS, "not set") ?: "not set"
    }

    actual fun setTestSettings(value: String) {
        sharedPreferences.edit()
            .putString(TEST_SETTINGS, value)
            .apply()
    }
}