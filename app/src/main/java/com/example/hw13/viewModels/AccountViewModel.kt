package com.example.hw13.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hw13.models.Account
import com.example.hw13.models.AccountType
import com.example.hw13.repository.Repository

open class AccountViewModel (App: Application): AndroidViewModel(App){

    var index=0
    var sizeList:Int=0
    var accountTypeLiveData=MutableLiveData<AccountType>()
    var nextEnabledLiveData=MutableLiveData(true)
    var backEnabledLiveData=MutableLiveData(false)
    var accountLiveData:LiveData<Account>?=null

    var listOfAccounts=ArrayList<Account>()
    var listLiveData:LiveData<List<Account>>?=null


    init {
        Repository.initDB(App.applicationContext)
    }

    fun setList(list:List<Account>){
        Repository.setAllAccounts(list)
    }
    fun getAllAccounts():List<Account>?{
        return Repository.getAllList()
    }

    fun getAllLiveData():LiveData<List<Account>>?{
        return Repository.getAllLiveData()
    }


    fun showAccount():Boolean{
        if (!Repository.getAccount(0)?.value?.cardNumber.isNullOrBlank()){
            listLiveData=Repository.getAllLiveData()
            sizeList= Repository.getAllList()?.size!!
            //accountTypeLiveData=listLiveData.value?.get(0)
            accountLiveData=Repository.getAccount(0)
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
        accountLiveData= Repository.getAccount(i)
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