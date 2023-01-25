package com.hoya.ddory.coconut.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hoya.ddory.coconut.database.entity.LogEntity

@Dao
interface LogDao {
    @Query("SELECT * FROM LOG_TABLE")
    suspend fun getLog(): List<LogEntity>

    @Insert
    suspend fun insertLog(log: LogEntity)
}
