package com.hoya.ddory.coconut.view.account

import android.content.Context
import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoya.ddory.coconut.database.entity.Account
import com.hoya.ddory.coconut.model.BithumbModel
import com.hoya.ddory.coconut.model.DatabaseModel
import kotlinx.coroutines.launch

class AccountViewModel : ViewModel() {

    private var _accountList = emptyList<Account>().toMutableStateList()
    val accountList: List<Account>
        get() = _accountList

    fun onResume(context: Context) {
        Log.i("JONGHO", "onResume")
        viewModelScope.launch {
            kotlin.runCatching {
                BithumbModel(context)
                    .getAccountKtor("BTC")
            }
                .onSuccess {
                    Log.i(TAG, "onSuccess")
                }
                .onFailure {
                    Log.i(TAG, "onFilure")
                }
        }
    }

    fun getTaskList(context: Context) {
        Log.i("JONGHO", "getTaskList")
        val databaseModel = DatabaseModel(context)
        viewModelScope.launch {
            _accountList.clear()
            _accountList.addAll(databaseModel.getAccounts())
        }
    }

    companion object {
        private const val TAG = "[coconut]AccountViewModel"
    }
}
