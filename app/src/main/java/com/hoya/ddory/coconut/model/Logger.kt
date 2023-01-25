package com.hoya.ddory.coconut.model

import android.content.Context
import com.hoya.ddory.coconut.database.CoconutDatabase
import com.hoya.ddory.coconut.database.LogDao
import com.hoya.ddory.coconut.database.entity.LogEntity
import com.hoya.ddory.coconut.database.entity.LogType
import java.util.*

class Logger(context: Context) {

    private val logDao = CoconutDatabase.getInstance(context).logDao()

    suspend fun http(tag: String, message: String) {
        logDao.insertLog(
            LogEntity(
                type = LogType.HTTP,
                date = Date(),
                tag = tag,
                message = message
            )
        )
    }

    suspend fun e(tag: String, message: String) {
        logDao.insertLog(
            LogEntity(
                type = LogType.ERROR,
                date = Date(),
                tag = tag,
                message = message
            )
        )
    }

    suspend fun i(tag: String, message: String) {
        logDao.insertLog(
            LogEntity(
                type = LogType.INFO,
                date = Date(),
                tag = tag,
                message = message
            )
        )
    }

    suspend fun d(tag: String, message: String) {
        logDao.insertLog(
            LogEntity(
                type = LogType.DEBUG,
                date = Date(),
                tag = tag,
                message = message
            )
        )
    }


}