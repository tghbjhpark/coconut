package com.hoya.ddory.coconut.view.account

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoya.ddory.coconut.model.BithumbModel
import kotlinx.coroutines.launch
import java.io.InputStream

class AccountViewModel : ViewModel() {
    private val _krw = MutableLiveData<String>().apply { value = "krw" }
    val krw: LiveData<String>
        get() = _krw

    private val _xrp = MutableLiveData<String>().apply { value = "xrp" }
    val xrp: LiveData<String>
        get() = _xrp

    private val _addAccountEvent: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val addAccountEvent: LiveData<Boolean>
        get() = _addAccountEvent

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

    fun onPause() {

    }

    fun addAccount() {
        Log.i(TAG, "addAccount")
        _addAccountEvent.postValue(true)
    }

    companion object {
        private const val TAG = "[coconut]AccountViewModel"
    }
}
