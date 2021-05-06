package com.hoya.ddory.coconut.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.hoya.ddory.coconut.database.entity.Account
import io.reactivex.Completable
import io.reactivex.Single

@Dao interface AccountDao {
    @Query("SELECT * FROM ACCOUNT_TABLE")
    fun getAccounts(): Single<List<Account>>

    @Query("SELECT * FROM ACCOUNT_TABLE WHERE id = :id")
    fun getAccount(id: Int): Single<Account>

    @Update
    fun updateAccount(account: Account): Completable

    @Delete
    fun deleteAccount(account: Account): Completable
}
