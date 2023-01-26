package com.hoya.ddory.coconut.model.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.hoya.ddory.coconut.model.BithumbModel
import com.hoya.ddory.coconut.model.DatabaseModel
import com.hoya.ddory.coconut.model.Logger
import java.util.concurrent.TimeUnit

class AutomationWorker(
    appContext: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(appContext, workerParameters) {

    private val log: Logger by lazy {
        Logger(applicationContext)
    }

    override suspend fun doWork(): Result {
        Log.i("JONGHO", "AutomationWorker")
        log.i("AutomationWorker", "doWork")
        val id = inputData.getInt(AUTOMATION_ACCOUNT_ID, -1)

        if (id == -1) return Result.failure()

        val account = DatabaseModel(applicationContext).getAccount(id)
        val price =
            BithumbModel(applicationContext).getTransactionHistory(account.orderCurrency).data.first().price.toFloat()
        log.i("AutomationWorker", "price:$price")

        if (account.quantity.toFloat() == 0f) {
            log.i("AutomationWorker", "quantity is 0")
            // buy amountAbove
            val input =
                WorkerManager.orderPlaceWorkerBuyData(id, account.amountBuyAbove.toInt(), price)
            val request = OneTimeWorkRequestBuilder<OrderPlaceWorker>()
                .setInputData(input)
                .addTag("account_id_$id")
                .build()
            WorkManager.getInstance(applicationContext).enqueue(request)
        } else {
            val target = account.average.toFloat() * 1.03
            log.i("AutomationWorker", "target:$target")
            if (price > account.average.toFloat() * 1.03) {
                log.i("AutomationWorker", "sell")
                // sell
                val input = WorkerManager.orderPlaceWorkerSellData(id, price)
                val request = OneTimeWorkRequestBuilder<OrderPlaceWorker>()
                    .setInputData(input)
                    .addTag("account_id_$id")
                    .build()
                WorkManager.getInstance(applicationContext).enqueue(request)
            } else {
                // buy
                if (price > account.average.toFloat()) {
                    if (account.available.toFloat() < account.amountBuyAbove.toFloat()) {
                        log.i("AutomationWorker", "not available (above)")
                        val inputData = WorkerManager.automationWorkerData(id)
                        val requester = OneTimeWorkRequestBuilder<AutomationWorker>()
                            .setInputData(inputData)
                            .addTag("account_id_$id")
                            .setInitialDelay(1L, TimeUnit.HOURS)
                            .build()
                        WorkManager.getInstance(applicationContext).enqueue(requester)
                        return Result.success()
                    }
                    // buy amountAbove
                    log.i("AutomationWorker", "but amount above")
                    val input = WorkerManager.orderPlaceWorkerBuyData(
                        id,
                        account.amountBuyAbove.toInt(),
                        price
                    )
                    val request = OneTimeWorkRequestBuilder<OrderPlaceWorker>()
                        .setInputData(input)
                        .addTag("account_id_$id")
                        .build()
                    WorkManager.getInstance(applicationContext).enqueue(request)
                } else {
                    if (account.available.toFloat() < account.amountBuyBelow.toFloat()) {
                        log.i("AutomationWorker", "not available (below)")
                        val inputData = WorkerManager.automationWorkerData(id)
                        val requester = OneTimeWorkRequestBuilder<AutomationWorker>()
                            .setInputData(inputData)
                            .setInitialDelay(1L, TimeUnit.HOURS)
                            .addTag("account_id_$id")
                            .build()
                        WorkManager.getInstance(applicationContext).enqueue(requester)
                        return Result.success()
                    }
                    // buy amountBelow
                    log.i("AutomationWorker", "buy amount below")
                    val input = WorkerManager.orderPlaceWorkerBuyData(
                        id,
                        account.amountBuyBelow.toInt(),
                        price
                    )
                    val request = OneTimeWorkRequestBuilder<OrderPlaceWorker>()
                        .setInputData(input)
                        .addTag("account_id_$id")
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
