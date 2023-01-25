package com.hoya.ddory.coconut.database

import androidx.room.TypeConverter
import com.hoya.ddory.coconut.database.entity.LogType
import java.util.Date

class LogConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromString(value: String?): LogType? {
        return value?.let { LogType.valueOf(value) }
    }

    @TypeConverter
    fun logTypeToString(type: LogType?): String? {
        return type?.name
    }
}
