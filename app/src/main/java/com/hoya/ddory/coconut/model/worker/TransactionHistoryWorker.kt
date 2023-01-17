package com.hoya.ddory.coconut.model.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.hoya.ddory.coconut.model.BithumbModel
import java.util.concurrent.TimeUnit

class TransactionHistoryWorker(
    appContext: Context,
    workerParams: WorkerParameters
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        BithumbModel(applicationContext).getTransactionHistory("XRP")
        val count = inputData.getInt("count", 0)
        if (count < 10) {
            retry(count)
        }

        return Result.success()
    }

    fun retry(count: Int) {
        val inputData = Data.Builder()
            .putInt("count", count + 1)
            .build()
        val workRequester = OneTimeWorkRequestBuilder<TransactionHistoryWorker>()
            .setInitialDelay(10L, TimeUnit.SECONDS)
            .setInputData(inputData)
            .addTag("transaction")
            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequester)
    }
}
