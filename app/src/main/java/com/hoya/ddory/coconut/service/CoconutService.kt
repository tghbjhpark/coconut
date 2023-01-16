package com.hoya.ddory.coconut.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class CoconutService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                START -> Unit
                STOP -> Unit
                else -> Unit
            }
        }
        return START_STICKY
    }

    companion object {
        const val START = "start"
        const val STOP = "stop"
    }
}