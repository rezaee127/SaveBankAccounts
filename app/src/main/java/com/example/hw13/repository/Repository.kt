package com.example.hw13.repository


import com.example.hw13.databases.Database3
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.hw13.databases.AccountDao
import com.example.hw13.models.Account
import com.example.hw13.models.AccountType


object Repository {

    var editProfileInfoFlag=false
    var listOfAccount=ArrayList<Account>()


    var db: Database3?=null
    var accountDao: AccountDao?=null


    fun initDB(context: Context){
        db= Database3.getMyDataBase(context)
        accountDao=db?.accountDao()

    }

    fun setAccount(account: Account){
        accountDao?.insertAccount(account)
    }

    fun setAllAccounts(list:List<Account>){
        accountDao?.insertAll(list)
    }

    fun delete(){
        accountDao?.deleteAll()
    }


    fun getAllList():List<Account>?{
        return accountDao?.getAll()
    }

    fun getAllLiveData(): LiveData<List<Account>>?{
        return accountDao?.getAllLiveData()
    }

    fun getBalance(cardNumber:String):Double?{
        return accountDao?.getBalanceByCardNumber(cardNumber)
    }

    fun getAccountType(cardNumber:String):AccountType?{
        return accountDao?.getAccountTypeByCardNumber(cardNumber)
    }


    fun getAccount(id:Int):LiveData<Account>?{
        return accountDao?.getAccount(id)
    }

}