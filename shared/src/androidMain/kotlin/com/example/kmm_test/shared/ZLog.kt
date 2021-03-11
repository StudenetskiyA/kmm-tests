package com.example.kmm_test.shared

import android.util.Log

actual class ZLog actual constructor(){
    //actual companion object {
        actual fun d(tag: String, message: String) {
            Log.d(tag, message)
        }
    //}
}