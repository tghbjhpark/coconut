package com.hoya.ddory.coconut.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hoya.ddory.coconut.database.entity.Account

@Database(
    entities = [Account::class],
    version = 1
)
abstract class CoconutDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao

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
