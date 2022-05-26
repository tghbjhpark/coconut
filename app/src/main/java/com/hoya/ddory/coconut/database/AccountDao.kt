package com.hoya.ddory.coconut.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.hoya.ddory.coconut.database.entity.Account

@Dao interface AccountDao {
    @Query("SELECT * FROM ACCOUNT_TABLE")
    suspend fun getAccounts(): List<Account>

    @Query("SELECT * FROM ACCOUNT_TABLE WHERE id = :id")
    suspend fun getAccount(id: Int): Account

    @Update
    suspend fun updateAccount(account: Account)

    @Delete
    suspend fun deleteAccount(account: Account)
}
