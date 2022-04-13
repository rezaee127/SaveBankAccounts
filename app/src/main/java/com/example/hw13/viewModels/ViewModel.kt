package com.example.hw13.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.hw13.models.Account
import com.example.hw13.repository.Repository

class ViewModel (App: Application): AndroidViewModel(App){

    var editProfileInfoFlag=Repository.editProfileInfoFlag
    var listOfAccounts=ArrayList<Account>()

    init {
        Repository.initDB(App.applicationContext)
    }

    fun setList(list:List<Account>){
        Repository.setAllAccounts(list)
    }
    fun getAllAccounts():List<Account>?{
        return Repository.getAll()
    }

}