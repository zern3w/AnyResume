package com.testanymind.data.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TechnologyUsedListConverter {
    @TypeConverter
    fun fromString(value: String?): List<String?>? =
        Gson().fromJson(value, object : TypeToken<List<String?>?>() {}.type)

    @TypeConverter
    fun fromList(value: List<String?>?): String? = Gson().toJson(value)
}