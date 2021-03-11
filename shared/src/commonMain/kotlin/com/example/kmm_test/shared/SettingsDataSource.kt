package com.example.kmm_test.shared


expect class SettingsDataSource() {
    fun getTestSettings() : String
    fun setTestSettings(value: String)
}