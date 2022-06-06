package com.hoya.ddory.coconut.view.addaccount

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AddAccountViewModel : ViewModel() {

    private var _deposit by mutableStateOf("100000")
    val deposit: String
        get() = _deposit

    fun changeDeposit(value: String) {
        _deposit = value
    }
}
