package com.jimenard.stockapp.repository.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {
    private var gson = Gson()

    @TypeConverter
    fun stringToProducts(data: String?): List<Stock> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Stock?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun productsToString(someObjects: List<Stock>): String {
        return gson.toJson(someObjects)
    }
}