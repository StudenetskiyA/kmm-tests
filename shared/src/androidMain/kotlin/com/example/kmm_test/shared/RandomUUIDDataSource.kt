package com.example.kmm_test.shared

import java.util.*

actual class RandomUUIDDataSource {
    actual fun getUUID(): String {
        return UUID.randomUUID().toString()
    }
}