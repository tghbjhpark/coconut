package com.hoya.ddory.coconut.model

import android.content.Context
import com.hoya.ddory.coconut.database.CoconutDatabase
import com.hoya.ddory.coconut.database.entity.LogEntity
import com.hoya.ddory.coconut.database.entity.LogType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class Logger(context: Context) {

    private val logDao = CoconutDatabase.getInstance(context).logDao()

    fun http(tag: String, message: String) = log(LogType.HTTP, tag, message)

    fun e(tag: String, message: String) = log(LogType.ERROR, tag, message)

    fun i(tag: String, message: String) = log(LogType.INFO, tag, message)

    fun d(tag: String, message: String) = log(LogType.DEBUG, tag, message)

    private fun log(type: LogType, tag: String, message: String) {
        GlobalScope.launch {
            logDao.insertLog(
                LogEntity(
                    type = type,
                    date = Date(),
                    tag = tag,
                    message = message
                )
            )
        }
    }
}
