package com.hoya.ddory.coconut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getTokenFromAsset()
    }

    fun getTokenFromAsset() {
        val assetmanager = resources.assets
        var inputStream: InputStream
        val key = ByteArray(32)
        val secret = ByteArray(32)

        inputStream = assetmanager.open("api_key")
        inputStream.read(key)
        inputStream.close()

        inputStream = assetmanager.open("api_secret")
        inputStream.read(secret)
        inputStream.close()

        Log.i(TAG, "${String(key)}, ${String(secret)}")
    }

    companion object {
        private const val TAG = "[coconut]MainActivity"
    }
}
