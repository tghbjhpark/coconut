package com.hoya.ddory.coconut.model.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.hoya.ddory.coconut.model.BithumbModel

class UserTransactionWorker(
    appContext: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        Log.i("JONGHO", "UserTransactionWorker")
        val id = inputData.getString(USER_TRANSACTION_ID).orEmpty()
        BithumbModel(applicationContext)
            .getOrdersKtor(id, "XRP")

        return Result.success()
    }

    companion object {
        const val USER_TRANSACTION_ID = "user_transaction_id"
    }
}
