package com.hoya.ddory.coconut.model.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.hoya.ddory.coconut.model.BithumbModel
import com.hoya.ddory.coconut.model.worker.UserTransactionWorker.Companion.USER_TRANSACTION_ID
import java.util.concurrent.TimeUnit

class OrderWorker(
    appContext: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        Log.i("JONGHO", "OrderWorker")
        val type = inputData.getInt(ORDER_TYPE, TYPE_BUY)
        val units = inputData.getFloat(ORDER_UNITS, 3f)
        if (type == TYPE_BUY) {
            BithumbModel(applicationContext)
                .buyKtor("XRP", units)
                .apply {
                    val requester = OneTimeWorkRequestBuilder<UserTransactionWorker>()
                        .setInputData(Data.Builder().putString(USER_TRANSACTION_ID, order_id).build())
                        .setInitialDelay(3L, TimeUnit.SECONDS)
                        .build()
                    WorkManager.getInstance(applicationContext).enqueue(requester)
                }
        }

        return Result.success()
    }

    companion object {
        const val ORDER_TYPE = "order_type"
        const val ORDER_UNITS = "order_units"

        const val TYPE_BUY = 0
        const val TYPE_SELL = 1
    }
}
