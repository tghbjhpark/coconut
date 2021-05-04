package com.hoya.ddory.coconut.model

import android.content.Context
import com.hoya.ddory.coconut.database.CoconutDatabase
import com.hoya.ddory.coconut.database.entity.Account

class DatabaseModel(context: Context) {

    val accountDao = CoconutDatabase.getInstance(context).accountDao()

    fun getAccounts() = accountDao.getAccounts()

    fun getAccount(id: Int) = accountDao.getAccount(id)

    fun updateAccount(account: Account) = accountDao.updateAccount(account)

    fun deleteAccount(account: Account) = accountDao.deleteAccount(account)
}
