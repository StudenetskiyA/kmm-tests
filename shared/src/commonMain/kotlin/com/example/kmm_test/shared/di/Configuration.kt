package com.example.kmm_test.shared.di

data class Configuration(
    val platform: PlatformType,
    val isDebug: Boolean
)

sealed class PlatformType {
    data class iOS(val version: String, val buildNumber: Int) : PlatformType()
    data class Android(val version: String, val buildNumber: Int) : PlatformType()
}