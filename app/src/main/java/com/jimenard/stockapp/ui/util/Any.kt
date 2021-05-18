package com.jimenard.stockapp.ui.util

import android.util.Log
import com.google.gson.Gson

/**
 * Se convierte cualquier tipo a un objeto json
 */
fun Any.toJson(): String = Gson().toJson(this)

fun <T> Class<in T>.logError(message: String) {
    Log.e(this::class.java.name, message)
}

fun Any.logError(message: String?) {
    Log.e(this::class.java.name, message?:"Error")
}
