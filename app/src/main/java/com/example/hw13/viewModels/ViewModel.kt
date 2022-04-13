package com.example.hw13.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hw13.models.Account
import com.example.hw13.models.AccountType
import com.example.hw13.repository.Repository

class ViewModel (App: Application): AndroidViewModel(App){

    var index=MutableLiveData(0)
    var sizeList:Int?=0
    var nextEnabledLiveData=MutableLiveData(true)
    var backEnabledLiveData=MutableLiveData(false)

    var editProfileInfoFlag=Repository.editProfileInfoFlag
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

    fun delete(){
        Repository.delete()
    }

    fun showAccount():Boolean{
        if (!Repository.getAllList()?.get(0)?.cardNumber.isNullOrBlank()){
            listLiveData=Repository.getAllLiveData()
            sizeList=listLiveData?.value?.size
            return true
        }else
            return false
    }

    private fun setQuestion(i: Int) {
        when (i) {
            0 -> backEnabledLiveData.value = false
            sizeList?.minus(1)  -> nextEnabledLiveData.value = false
            else -> {
                nextEnabledLiveData.value = true
                backEnabledLiveData.value = true
            }
        }
        //questionLiveData.value =Repository.questionList[i].question
    }


    fun nextClicked() {
        index.value = index.value?.plus(1)
        index.value?.let {
            setQuestion(it)
        }
    }

    fun backClicked() {
        index.value = index.value?.minus(1)
        index.value?.let {
            setQuestion(it)
        }
    }

}