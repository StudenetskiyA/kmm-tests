package com.example.kmm_test.shared

expect class RandomUUIDDataSource() {
    fun getUUID(): String
}