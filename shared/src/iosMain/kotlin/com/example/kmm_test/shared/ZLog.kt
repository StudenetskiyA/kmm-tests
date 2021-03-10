package com.example.kmm_test.shared

actual class ZLog {
    actual companion object {
        actual fun d(tag: String, message: String) {
            print("$tag : $message")
        }
    }
}