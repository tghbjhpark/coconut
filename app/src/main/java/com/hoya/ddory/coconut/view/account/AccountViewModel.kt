package com.hoya.ddory.coconut.view.account

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.hoya.ddory.coconut.database.entity.Account
import com.hoya.ddory.coconut.model.DatabaseModel
import com.hoya.ddory.coconut.model.worker.AutomationWorker
import com.hoya.ddory.coconut.model.worker.OrderPlaceWorker
import com.hoya.ddory.coconut.model.worker.OrderWorker
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
            stop()
        } else {
            Log.i("JONGHO", "current STOP")
            account.state = "START"
            start(id, context)
        }
        viewModelScope.launch {
            databaseModel.updateAccount(account)
            accountList.clear()
            accountList.addAll(databaseModel.getAccounts())
        }
    }

    private fun start(id: Int, context: Context) {
        val inputData = Data.Builder()
            .putInt(AutomationWorker.AUTOMATION_ACCOUNT_ID, id)
            .build()
        val requester = OneTimeWorkRequestBuilder<AutomationWorker>()
            .setInputData(inputData)
            .build()
        WorkManager.getInstance(context).enqueue(requester)
    }

    private fun stop() {

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
