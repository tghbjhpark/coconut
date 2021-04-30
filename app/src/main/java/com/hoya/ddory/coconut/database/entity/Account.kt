package com.hoya.ddory.coconut.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "account_table"
)
class Account {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "created")
    var created: String = "0"

    @ColumnInfo(name = "order_currency")
    var orderCurrency: String = "BTC"

    @ColumnInfo(name = "payment_currency")
    var paymentCurrency: String = "KRW"

    @ColumnInfo(name = "init_value")
    var initValue: String = "0"

    @ColumnInfo(name = "available_value")
    var available: String = "0"

    @ColumnInfo(name = "current_payment")
    var currentPayment: String = "0"

    @ColumnInfo(name = "interval_sec")
    var intervalSec: Int = 3600

    @ColumnInfo(name = "unit")
    var unit: String = "0"

    @ColumnInfo(name = "state")
    var state: String = "STOP"
}
