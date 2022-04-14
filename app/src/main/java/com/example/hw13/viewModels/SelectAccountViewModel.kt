package com.example.hw13.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.hw13.models.Account
import com.example.hw13.models.AccountType
import com.example.hw13.repository.Repository

class SelectAccountViewModel (App: Application): AndroidViewModel(App){

    lateinit var accountLiveData: LiveData<Account>
    lateinit var accountType: LiveData<AccountType>

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

    fun getAccountByCardNumber(card: String):LiveData<Account>{
        return Repository.getAccountByCardNumber(card)
    }


    fun getBalanceByCardNumber(card:String):Double{
        return Repository.getBalanceByCardNumber(card)
    }

    fun getAccountTypeByCardNumber(card:String):AccountType{
        return Repository.getAccountTypeByCardNumber(card)
    }



//    fun getBalanceByCardNumberLiveData(card:String): LiveData<Double> {
//        return Repository.getBalanceByCardNumberLiveData(card)
//    }

    fun getAccountTypeByCardNumberLiveData(card:String):LiveData<AccountType>{
        return Repository.getAccountTypeByCardNumberLiveData(card)
    }


}