package com.example.hw13.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.hw13.models.Account
import com.example.hw13.models.AccountType
import com.example.hw13.repository.Repository

open class AccountViewModel (App: Application): AndroidViewModel(App){

    var index=0
    var sizeList:Int=0
    var listOfAccounts:List<Account>?=null
    var cardNumberLiveData=MutableLiveData<String>()
    var balanceLiveData=MutableLiveData<Double>()
    var accountTypeLiveData=MutableLiveData<AccountType>()
    var accountType=Transformations.map(accountTypeLiveData){
        when(it){
            AccountType.SavingsAccount -> "پس انداز"
            AccountType.LongTerm -> "بلند مدت"
            else -> "کوتاه مدت"
        }
    }

    var nextEnabledLiveData=MutableLiveData(true)
    var backEnabledLiveData=MutableLiveData(false)
    var accountLiveData:LiveData<Account>?=null


    var listLiveData:LiveData<List<Account>>?=null


    init {
        Repository.initDB(App.applicationContext)
        showAccount()
    }


    fun getAllAccounts():List<Account>?{
        return Repository.getAllList()
    }

    fun getAllLiveData():LiveData<List<Account>>?{
        return Repository.getAllLiveData()
    }


    fun showAccount():Boolean{
        if (Repository.getAllList()?.size!=0){
            listOfAccounts=Repository.getAllList()
            sizeList= Repository.getAllList()?.size!!
            cardNumberLiveData.value= listOfAccounts?.get(0)?.cardNumber
            balanceLiveData.value= listOfAccounts?.get(0)?.balance
            accountTypeLiveData.value= listOfAccounts?.get(0)?.accountType
            accountLiveData=Repository.getAccountLiveData(0)
            return true
        }else
            return false
    }

    private fun setQuestion(i: Int) {
        when (i) {
            0 -> {
                backEnabledLiveData.value = false
                nextEnabledLiveData.value = true
            }
            sizeList-1  ->{
                nextEnabledLiveData.value = false
                backEnabledLiveData.value = true
            }
            else -> {
                nextEnabledLiveData.value = true
                backEnabledLiveData.value = true
            }
        }
        accountLiveData= Repository.getAccountLiveData(i)
        cardNumberLiveData.value= listOfAccounts?.get(i)?.cardNumber
        balanceLiveData.value= listOfAccounts?.get(i)?.balance
        accountTypeLiveData.value= listOfAccounts?.get(i)?.accountType
        //listLiveData?.value=listLiveData?.value[]
        //questionLiveData.value =Repository.questionList[i].question
    }


    fun nextClicked() {
        index++
        setQuestion(index)

    }

    fun backClicked() {
        index--
        setQuestion(index)

//        index.value = index.value?.minus(1)
//        index.value?.let {
//            setQuestion(it)
//        }
    }

}