package com.hoya.ddory.coconut.model.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.hoya.ddory.coconut.model.BithumbModel
import com.hoya.ddory.coconut.model.DatabaseModel
import java.util.concurrent.TimeUnit

class AutomationWorker(
    appContext: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        Log.i("JONGHO", "AutomationWorker")
        val id = inputData.getInt(AUTOMATION_ACCOUNT_ID, -1)

        if (id == -1) return Result.failure()

        val account = DatabaseModel(applicationContext).getAccount(id)
        val price = BithumbModel(applicationContext).getTransactionHistory(account.orderCurrency).data.first().price.toFloat()


        if (account.quantity.toFloat() == 0f) {
            // buy amountAbove
            val input = Data.Builder()
                .putInt(OrderPlaceWorker.ORDER_TYPE, OrderPlaceWorker.TYPE_BUY)
                .putInt(OrderPlaceWorker.ORDER_AMOUNT, account.amountBuyAbove.toInt())
                .putInt(OrderPlaceWorker.ORDER_ACCOUNT_ID, id)
                .putFloat(OrderPlaceWorker.ORDER_PRICE, price)
                .build()
            val request = OneTimeWorkRequestBuilder<OrderPlaceWorker>()
                .setInputData(input)
                .build()
            WorkManager.getInstance(applicationContext).enqueue(request)
        } else {
            if (price > account.average.toFloat() * 1.03) {
                // sell
                val input = Data.Builder()
                    .putInt(OrderPlaceWorker.ORDER_TYPE, OrderPlaceWorker.TYPE_SELL)
                    .putInt(OrderPlaceWorker.ORDER_ACCOUNT_ID, id)
                    .putFloat(OrderPlaceWorker.ORDER_PRICE, price)
                    .build()
                val request = OneTimeWorkRequestBuilder<OrderPlaceWorker>()
                    .setInputData(input)
                    .build()
                WorkManager.getInstance(applicationContext).enqueue(request)
            } else {
                // buy
                if (price > account.average.toFloat()) {
                    if (account.available.toFloat() < account.amountBuyAbove.toFloat()) {
                        val inputData = Data.Builder()
                            .putInt(AutomationWorker.AUTOMATION_ACCOUNT_ID, id)
                            .build()
                        val requester = OneTimeWorkRequestBuilder<AutomationWorker>()
                            .setInputData(inputData)
                            .setInitialDelay(1L, TimeUnit.HOURS)
                            .build()
                        WorkManager.getInstance(applicationContext).enqueue(requester)
                        return Result.success()
                    }
                    // buy amountAbove
                    val input = Data.Builder()
                        .putInt(OrderPlaceWorker.ORDER_TYPE, OrderPlaceWorker.TYPE_BUY)
                        .putInt(OrderPlaceWorker.ORDER_AMOUNT, account.amountBuyAbove.toInt())
                        .putInt(OrderPlaceWorker.ORDER_ACCOUNT_ID, id)
                        .putFloat(OrderPlaceWorker.ORDER_PRICE, price)
                        .build()
                    val request = OneTimeWorkRequestBuilder<OrderPlaceWorker>()
                        .setInputData(input)
                        .build()
                    WorkManager.getInstance(applicationContext).enqueue(request)
                } else {
                    if (account.available.toFloat() < account.amountBuyBelow.toFloat()) {
                        val inputData = Data.Builder()
                            .putInt(AutomationWorker.AUTOMATION_ACCOUNT_ID, id)
                            .build()
                        val requester = OneTimeWorkRequestBuilder<AutomationWorker>()
                            .setInputData(inputData)
                            .setInitialDelay(1L, TimeUnit.HOURS)
                            .build()
                        WorkManager.getInstance(applicationContext).enqueue(requester)
                        return Result.success()
                    }
                    // buy amountBelow
                    val input = Data.Builder()
                        .putInt(OrderPlaceWorker.ORDER_TYPE, OrderPlaceWorker.TYPE_BUY)
                        .putInt(OrderPlaceWorker.ORDER_AMOUNT, account.amountBuyBelow.toInt())
                        .putInt(OrderPlaceWorker.ORDER_ACCOUNT_ID, id)
                        .putFloat(OrderPlaceWorker.ORDER_PRICE, price)
                        .build()
                    val request = OneTimeWorkRequestBuilder<OrderPlaceWorker>()
                        .setInputData(input)
                        .build()
                    WorkManager.getInstance(applicationContext).enqueue(request)
                }
            }
        }

        return Result.success()

    }

    companion object {
        const val AUTOMATION_ACCOUNT_ID = "automation_account_id"
    }
}
