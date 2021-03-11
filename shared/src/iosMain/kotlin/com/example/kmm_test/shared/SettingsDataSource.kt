package com.example.kmm_test.shared

import platform.Foundation.NSUserDefaults
import platform.Foundation.setValue

actual class SettingsDataSource {
    companion object {
        const val TEST_SETTINGS = "test_settings"
    }

    private val preferences = NSUserDefaults

    actual fun getTestSettings(): String {
        return preferences.standardUserDefaults.stringForKey(TEST_SETTINGS) ?: "not set"
    }

    actual fun setTestSettings(value: String) {
        println("skyfolk-kmm: set value for ${TEST_SETTINGS} = ${value}")
        preferences.standardUserDefaults.setValue(value, forKey = TEST_SETTINGS)
    }
}