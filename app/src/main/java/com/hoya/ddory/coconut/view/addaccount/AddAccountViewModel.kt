package com.hoya.ddory.coconut.view.addaccount

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.hoya.ddory.coconut.data.Coin

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
}
