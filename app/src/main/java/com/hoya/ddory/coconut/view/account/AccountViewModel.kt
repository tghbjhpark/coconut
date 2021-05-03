package com.hoya.ddory.coconut.view.account

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hoya.ddory.coconut.model.BithumbModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.InputStream

class AccountViewModel : ViewModel() {
    private val _krw = MutableLiveData<String>().apply { value = "krw" }
    val krw: LiveData<String>
        get() = _krw

    private val _xrp = MutableLiveData<String>().apply { value = "xrp" }
    val xrp: LiveData<String>
        get() = _xrp

    fun onResume(context: Context) {
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
        BithumbModel(String(key), String(secret))
            .getBalance()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.i(TAG, "krw:${it.data.krw},xrp:${it.data.xrp}")
                    _krw.value = it.data.krw
                    _xrp.value = it.data.xrp
                },
                {

                }
            )
    }

    fun onPause() {

    }

    fun addAccount() {
        Log.i(TAG, "addAccount")
    }

    companion object {
        private const val TAG = "[coconut]AccountViewModel"
    }
}
