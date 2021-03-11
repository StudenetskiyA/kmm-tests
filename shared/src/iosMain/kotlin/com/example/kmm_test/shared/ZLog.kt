package com.example.kmm_test.shared

actual class ZLog actual constructor() {
    //actual companion object {
        actual fun d(tag: String, message: String) {
            println("$tag : $message")
      //  }
    }
}