package com.example.taskmanagementappusingroomdatabase.data.local.converters

import androidx.room.TypeConverter
import java.util.Date

class StringListConverter {
    @TypeConverter
    fun fromListStringToString(list: List<String>): String
    {
        return list.joinToString(",")
    }

    @TypeConverter
    fun fromStringToListString(string: String): List<String>
    {
        return string.split(",")
    }
}