package com.hoya.ddory.coconut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
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
    
    @Composable
    private fun MainScreen() {
        MaterialTheme {
            Text(text = "Hello World!!")
        }
    }

    companion object {
        private const val TAG = "[coconut]MainActivity"
    }
}
