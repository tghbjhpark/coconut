package com.hoya.ddory.coconut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hoya.ddory.coconut.view.CoconutNavHost
import java.io.InputStream

@ExperimentalMaterial3Api
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
            val navController = rememberNavController()
            val backstackEntry = navController.currentBackStackEntryAsState()
            CoconutNavHost(navController = navController)
        }
    }

    companion object {
        private const val TAG = "[coconut]MainActivity"
    }
}
