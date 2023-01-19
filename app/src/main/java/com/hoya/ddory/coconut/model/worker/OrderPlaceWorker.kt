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

class OrderPlaceWorker(
    appContext: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {

        val type = inputData.getInt(ORDER_TYPE, OrderWorker.TYPE_BUY)
        val price = inputData.getFloat(ORDER_PRICE, -1f)
        val id = inputData.getInt(ORDER_ACCOUNT_ID, -1)
        val amount = inputData.getInt(ORDER_AMOUNT, 0)
        if (id == -1) return Result.failure()

        if (price < 0) return Result.failure()

        if (amount == 0) return Result.failure()

        val bithumbModel = BithumbModel(applicationContext)

        if (type == TYPE_BUY) {
            val units: Int = (amount / price).toInt()

            bithumbModel.buyKtor("XRP", price.toString(), units.toFloat())
                .apply {
                    val requester = OneTimeWorkRequestBuilder<UserTransactionWorker>()
                        .setInputData(
                            Data.Builder()
                                .putString(UserTransactionWorker.USER_TRANSACTION_ID, order_id)
                                .putInt(UserTransactionWorker.USER_TRANSACTION_ACCOUNT_ID, id)
                                .build()
                        )
                        .setInitialDelay(1L, TimeUnit.MINUTES)
                        .addTag("account_id_$id")
                        .build()
                    WorkManager.getInstance(applicationContext).enqueue(requester)
                }
        } else {
            val databaseModel = DatabaseModel(applicationContext)
            val account = databaseModel.getAccount(id)
            bithumbModel.sellKtor("XRP", price.toString(), account.quantity.toFloat())
                .apply {
                    val requester = OneTimeWorkRequestBuilder<UserTransactionWorker>()
                        .setInputData(
                            Data.Builder()
                                .putString(UserTransactionWorker.USER_TRANSACTION_ID, order_id)
                                .putInt(UserTransactionWorker.USER_TRANSACTION_ACCOUNT_ID, id)
                                .build()
                        )
                        .setInitialDelay(1L, TimeUnit.MINUTES)
                        .addTag("account_id_$id")
                        .build()
                    WorkManager.getInstance(applicationContext).enqueue(requester)
                }
        }

        return Result.success()
    }

    companion object {
        const val ORDER_TYPE = "order_type"
        const val ORDER_PRICE = "order_price"
        const val ORDER_AMOUNT = "order_amount"
        const val ORDER_ACCOUNT_ID = "order_account_id"

        const val TYPE_BUY = 0
        const val TYPE_SELL = 1
    }
}
