package com.hoya.ddory.coconut.model

import android.content.Context
import com.hoya.ddory.coconut.database.CoconutDatabase
import com.hoya.ddory.coconut.database.entity.Account

class DatabaseModel(context: Context) {

    private val accountDao = CoconutDatabase.getInstance(context).accountDao()

    suspend fun getAccounts() = accountDao.getAccounts()

    suspend fun getAccount(id: Int) = accountDao.getAccount(id)

    suspend fun insertAccount(account: Account) = accountDao.insertAccount(account)

    suspend fun updateAccount(account: Account) = accountDao.updateAccount(account)

    suspend fun deleteAccount(account: Account) = accountDao.deleteAccount(account)
}
