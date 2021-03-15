package com.example.kmm_test.shared

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970
import kotlin.math.roundToLong

actual class DateTimeDataSource {
    actual fun getTimeInMillis(): Long {
        return (NSDate().timeIntervalSince1970 * 1000).roundToLong()
    }
}