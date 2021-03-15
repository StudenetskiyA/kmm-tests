package com.example.kmm_test.shared

import platform.Foundation.NSUUID

actual class RandomUUIDDataSource {
    actual fun getUUID(): String {
        return NSUUID().UUIDString
    }
}