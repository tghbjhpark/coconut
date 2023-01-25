package com.hoya.ddory.coconut.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hoya.ddory.coconut.database.entity.Account
import com.hoya.ddory.coconut.database.entity.LogEntity

@Database(
    entities = [Account::class, LogEntity::class],
    version = 1
)
@TypeConverters(LogConverters::class)
abstract class CoconutDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao

    abstract fun logDao(): LogDao

    companion object {
        private var INSTANCE: CoconutDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): CoconutDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CoconutDatabase::class.java,
                        "Coconut.db"
                    )
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}
