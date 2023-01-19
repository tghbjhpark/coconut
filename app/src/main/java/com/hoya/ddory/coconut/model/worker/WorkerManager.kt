package com.hoya.ddory.coconut.model.worker

import androidx.work.Data

object WorkerManager {

    @JvmStatic
    fun automationWorkerData(id: Int) =
        Data.Builder()
            .putInt(AutomationWorker.AUTOMATION_ACCOUNT_ID, id)
            .build()

    @JvmStatic
    fun orderPlaceWorkerBuyData(id: Int, amount: Int, price: Float) =
        Data.Builder()
            .putInt(OrderPlaceWorker.ORDER_TYPE, OrderPlaceWorker.TYPE_BUY)
            .putInt(OrderPlaceWorker.ORDER_AMOUNT, amount)
            .putInt(OrderPlaceWorker.ORDER_ACCOUNT_ID, id)
            .putFloat(OrderPlaceWorker.ORDER_PRICE, price)
            .build()

    @JvmStatic
    fun orderPlaceWorkerSellData(id: Int, price: Float) =
        Data.Builder()
            .putInt(OrderPlaceWorker.ORDER_TYPE, OrderPlaceWorker.TYPE_SELL)
            .putInt(OrderPlaceWorker.ORDER_ACCOUNT_ID, id)
            .putFloat(OrderPlaceWorker.ORDER_PRICE, price)
            .build()

    @JvmStatic
    fun userTransactionWorkerData(id: Int, orderId: String, count: Int = 0) =
        Data.Builder()
            .putString(UserTransactionWorker.USER_TRANSACTION_ID, orderId)
            .putInt(UserTransactionWorker.USER_TRANSACTION_ACCOUNT_ID, id)
            .putInt(UserTransactionWorker.USER_TRANSACTION_COUNT, count)
            .build()
}
