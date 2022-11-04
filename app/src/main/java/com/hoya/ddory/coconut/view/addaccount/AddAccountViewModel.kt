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

    fun changeDeposit(value: String) {
        _deposit = value
    }

    fun coinNameResourceIdList(): List<Int> {
        return Coin.values().map {
            it.nameResource
        }
    }
}
