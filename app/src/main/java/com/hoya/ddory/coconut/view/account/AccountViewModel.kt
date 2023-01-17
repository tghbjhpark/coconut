package com.hoya.ddory.coconut.view.account

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoya.ddory.coconut.database.entity.Account
import com.hoya.ddory.coconut.model.DatabaseModel
import kotlinx.coroutines.launch

class AccountViewModel : ViewModel() {

    var accountList = mutableStateListOf<Account>()
        private set

    fun playPause(id: Int, context: Context) {
        Log.i("JONGHO", "playPause:$id")
        val account = accountList.first { it.id == id }
        val databaseModel = DatabaseModel(context)
        if (account.state == "START") {
            Log.i("JONGHO", "current START")
            account.state = "STOP"
        } else {
            Log.i("JONGHO", "current STOP")
            account.state = "START"
        }
        viewModelScope.launch {
            databaseModel.updateAccount(account)
            accountList.clear()
            accountList.addAll(databaseModel.getAccounts())
        }
    }

    fun getTaskList(context: Context) {
        Log.i("JONGHO", "getTaskList")
        val databaseModel = DatabaseModel(context)
        viewModelScope.launch {
            accountList.clear()
            accountList.addAll(databaseModel.getAccounts())
        }
    }

    companion object {
        private const val TAG = "[coconut]AccountViewModel"
    }
}
