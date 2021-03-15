package com.example.kmm_test.shared

import java.util.*

actual class DateTimeDataSource {
    actual fun getTimeInMillis() : Long {
        return System.currentTimeMillis()
    }
}