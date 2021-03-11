package com.example.kmm_test.shared

interface ISettingsInteractor {
    fun getTestSettings() : String
    fun setTestSettings(value: String)
}

class SettingsInteractor(private val dataSource: SettingsDataSource): ISettingsInteractor {
    override fun getTestSettings(): String {
       return dataSource.getTestSettings()
    }

    override fun setTestSettings(value: String) {
       dataSource.setTestSettings(value)
    }
}