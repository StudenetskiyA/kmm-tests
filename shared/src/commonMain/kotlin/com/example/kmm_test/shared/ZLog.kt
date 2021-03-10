package com.example.kmm_test.shared

expect class ZLog {
    companion object {
        fun d(tag: String, message: String)
    }
}