package com.hoya.ddory.coconut.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_table")
data class Account(
    @ColumnInfo(name = "order_currency") val orderCurrency: String,
    @ColumnInfo(name = "payment_currency") val paymentCurrency: String = "KRW",
    @ColumnInfo(name = "init_deposit") val initDeposit: String,
    @ColumnInfo(name = "amount_buy_above") val amountBuyAbove: String,
    @ColumnInfo(name = "amount_buy_below") val amountBuyBelow: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "created")
    var created: String = System.currentTimeMillis().toString()

    @ColumnInfo(name = "current_deposit")
    var available: String = "0"

    @ColumnInfo(name = "quantity")
    var quantity: String = "0"

    @ColumnInfo(name = "fee")
    var fee: String = "0"

    @ColumnInfo(name = "interval_sec")
    var intervalSec: Int = 3600

    @ColumnInfo(name = "average")
    var average: String = "0"

    @ColumnInfo(name = "state")
    var state: String = "STOP"
}
