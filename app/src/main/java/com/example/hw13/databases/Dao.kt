
package com.example.hw13.databases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.hw13.models.Account
import com.example.hw13.models.AccountType

@Dao
interface AccountDao {

    @Query("SELECT balance FROM Account where cardNumber=:cardNumber")
    fun getBalanceByCardNumber(cardNumber:String):Double


    @Query("SELECT accountType FROM Account where cardNumber=:cardNumber")
    fun getAccountTypeByCardNumber(cardNumber:String): AccountType

    @Query("SELECT * FROM Account where id=:id")
    fun getAccountLiveData(id:Int): LiveData<Account>

    @Query("SELECT * FROM Account where id=:id")
    fun getAccount(id:Int): Account


//    @Query("SELECT * FROM Account where id=:id")
//    fun getAccount(id:Int): LiveData<Account>

    @Query("SELECT * FROM Account")
    fun getAll():List<Account>

    @Query("SELECT * FROM Account")
    fun getAllLiveData(): LiveData<List<Account>>


//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(vararg account: Account)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list:List<Account>)



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAccount(vararg account: Account)

    @Query("DELETE FROM Account")
    fun deleteAll()

}