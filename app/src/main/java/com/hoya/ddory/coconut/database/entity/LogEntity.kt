package com.hoya.ddory.coconut.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "log_table")
data class LogEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name ="date") val date: Date,
    @ColumnInfo(name = "type") val type: LogType,
    @ColumnInfo(name = "tag") val tag: String,
    @ColumnInfo(name = "message") val message: String
)

enum class LogType {
    HTTP,
    ERROR,
    INFO,
    DEBUG
}
