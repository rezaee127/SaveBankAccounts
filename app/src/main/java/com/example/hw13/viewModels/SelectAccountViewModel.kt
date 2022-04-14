package com.example.hw13.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.hw13.models.Account
import com.example.hw13.models.AccountType
import com.example.hw13.repository.Repository

class SelectAccountViewModel (App: Application): AndroidViewModel(App){

    init {
        Repository.initDB(App.applicationContext)
    }

    fun getAllAccounts():List<Account>{
        return Repository.getAllList()
    }

    fun getCardNumber(card:String):Boolean{
        var flag=false
        var size=Repository.getAllList().size
        for (i in 0 until size){
            if(card== Repository.getAllList()[i].cardNumber){
               flag= true
               break
            }else
                flag= false
        }
        return flag
    }

    fun getBalance(card:String):Double{
        return Repository.getBalance(card)
    }

    fun getAccountType(card:String):AccountType{
        return Repository.getAccountType(card)
    }


}