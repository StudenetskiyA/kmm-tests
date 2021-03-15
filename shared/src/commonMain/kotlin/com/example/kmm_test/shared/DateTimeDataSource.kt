package com.example.kmm_test.shared

expect class DateTimeDataSource() {
    fun getTimeInMillis() : Long
}