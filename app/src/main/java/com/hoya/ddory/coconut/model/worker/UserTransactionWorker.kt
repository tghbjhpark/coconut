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

class UserTransactionWorker(
    appContext: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        Log.i("JONGHO", "UserTransactionWorker")
        val id = inputData.getString(USER_TRANSACTION_ID).orEmpty()
        val accountId = inputData.getInt(USER_TRANSACTION_ACCOUNT_ID, -1)
        val count = inputData.getInt(USER_TRANSACTION_COUNT, 0)
        BithumbModel(applicationContext)
            .getOrdersKtor(id, "XRP")
            .apply {
                if (data.order_status != "Completed" && count < 10) {
                    val requester = OneTimeWorkRequestBuilder<UserTransactionWorker>()
                        .setInputData(Data.Builder()
                            .putString(USER_TRANSACTION_ID, id)
                            .putInt(USER_TRANSACTION_COUNT, count + 1)
                            .build())
                        .setInitialDelay(1L, TimeUnit.MINUTES)
                        .build()
                    WorkManager.getInstance(applicationContext).enqueue(requester)
                } else {
                    if (data.order_status == "Completed") {
                        val database = DatabaseModel(applicationContext)
                        val accountInfo = database.getAccount(accountId)
                        if (data.type == "bid") {
                            // buy
                            var currentTotal = accountInfo.average.toFloat() * accountInfo.quantity.toFloat()
                            val orderTotal = data.order_qty.toFloat() * data.order_price.toFloat()

                            var totalFee = 0f
                            data.contract.forEach {
                                totalFee += it.fee.toFloat()
                            }
                            accountInfo.fee = (accountInfo.fee.toFloat() + totalFee).toString()
                            accountInfo.available = (accountInfo.available.toFloat() - orderTotal - totalFee).toString()
                            accountInfo.quantity = (accountInfo.quantity.toFloat() + data.order_qty.toFloat()).toString()
                            accountInfo.average = ((currentTotal + orderTotal) / (accountInfo.quantity.toFloat())).toString()
                        } else {
                            // sell
                            var totalFee = 0f
                            data.contract.forEach {
                                totalFee += it.fee.toFloat()
                            }
                            val orderTotal = data.order_qty.toFloat() * data.order_price.toFloat()
                            accountInfo.fee = (accountInfo.fee.toFloat() + totalFee).toString()
                            accountInfo.quantity = "0"
                            accountInfo.average = "0"
                            accountInfo.available = (accountInfo.available.toFloat() + orderTotal - totalFee).toString()
                        }
                        database.updateAccount(accountInfo)

                        val inputData = Data.Builder()
                            .putInt(AutomationWorker.AUTOMATION_ACCOUNT_ID, accountId)
                            .build()
                        val requester = OneTimeWorkRequestBuilder<AutomationWorker>()
                            .setInputData(inputData)
                            .setInitialDelay(1L, TimeUnit.HOURS)
                            .build()
                        WorkManager.getInstance(applicationContext).enqueue(requester)
                    }
                }
            }

        return Result.success()
    }

    companion object {
        const val USER_TRANSACTION_ID = "user_transaction_id"
        const val USER_TRANSACTION_COUNT = "user_transaction_count"
        const val USER_TRANSACTION_ACCOUNT_ID = "user_transaction_account_id"
    }
}
