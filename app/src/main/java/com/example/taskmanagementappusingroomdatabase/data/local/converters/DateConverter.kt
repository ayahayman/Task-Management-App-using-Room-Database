package com.example.taskmanagementappusingroomdatabase.data.local.converters

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun fromTimestampToDate(time: Long): Date
    {
        return Date(time)
    }

    @TypeConverter
    fun fromDateToTimestamp(date: Date): Long
    {
        return date.time
    }
}