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
import java.io.InputStream

class AccountViewModel : ViewModel() {

    private var _accountList = emptyList<Account>().toMutableStateList()
    val accountList: List<Account>
        get() = _accountList

    fun onResume(context: Context) {
        Log.i("JONGHO", "onResume")
        val assetmanager = context.resources.assets
        var inputStream: InputStream
        val key = ByteArray(32)
        val secret = ByteArray(32)

        inputStream = assetmanager.open("api_key")
        inputStream.read(key)
        inputStream.close()

        inputStream = assetmanager.open("api_secret")
        inputStream.read(secret)
        inputStream.close()
        viewModelScope.launch {
            kotlin.runCatching {
                BithumbModel(String(key), String(secret))
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
