package com.hoya.ddory.coconut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hoya.ddory.coconut.view.account.AccountFragment

class DevelopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_develop)

        supportFragmentManager.findFragmentById(R.id.frame)
            ?: supportFragmentManager.beginTransaction().apply {
                replace(R.id.frame, AccountFragment.newInstance())
            }.commit()
    }
}
