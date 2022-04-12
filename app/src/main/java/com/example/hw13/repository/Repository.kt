package com.example.hw13.repository


import android.content.Context
import com.example.hw13.databases.AccountDao

import com.example.hw13.models.Account
import com.example.hw13.models.AccountType


object Repository {
    //var numberOfAccount=1
    var editProfileInfoFlag=false
    var listOfAccount=ArrayList<Account>()



   // var db: MyDatabase?=null
    var accountDao: AccountDao?=null

    fun initDB(context: Context){
        //db= MyDatabase.getMyDataBase(context)
        //accountDao=db?.accountDao()

    }
    fun setAccount(account: Account){
        accountDao?.insertAccount(account)
    }


    fun getAll():List<Account>?{
        return accountDao?.getAll()
    }

    fun getBalance(cardNumber:Int):Double?{
        return accountDao?.getBalanceByCardNumber(cardNumber)
    }

    fun getAccountType(cardNumber:Int):AccountType?{
        return accountDao?.getAccountTypeByCardNumber(cardNumber)
    }



}