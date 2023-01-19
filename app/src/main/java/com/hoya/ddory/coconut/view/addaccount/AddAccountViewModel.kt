package com.hoya.ddory.coconut.view.addaccount

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoya.ddory.coconut.data.Coin
import com.hoya.ddory.coconut.database.entity.Account
import com.hoya.ddory.coconut.model.DatabaseModel
import kotlinx.coroutines.launch

class AddAccountViewModel : ViewModel() {

    private var _deposit by mutableStateOf("100000")
    val deposit: String
        get() = _deposit

    private var _buyAbove by mutableStateOf("3000")
    val buyAbove: String
        get() = _buyAbove

    private var _buyBelow by mutableStateOf("5000")
    val buyBelow: String
        get() = _buyBelow

    fun changeDeposit(value: String) {
        _deposit = value
    }

    fun changeBuyAbove(value: String) {
        _buyAbove = value
    }

    fun changeBuyBelow(value: String) {
        _buyBelow = value
    }

    fun coinNameResourceIdList(): List<Int> {
        return Coin.values().map {
            it.nameResource
        }
    }

    fun createAccount(context: Context) {
        // TODO :: change orderCurrency
        val account = Account(
            orderCurrency = "XRP",
            paymentCurrency = "KRW",
            initDeposit = deposit,
            amountBuyAbove = buyAbove,
            amountBuyBelow = buyBelow,
        ).apply {
            available = initDeposit
        }
        val databaseModel = DatabaseModel(context)
        viewModelScope.launch {
            databaseModel.insertAccount(account)
        }
    }
}
