package com.example.hw13.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.hw13.models.Account
import com.example.hw13.repository.Repository

class CreateAccountViewModel(App: Application): AndroidViewModel(App) {
    var listOfAccount=ArrayList<Account>()

    init {
        Repository.initDB(App.applicationContext)
    }


    fun setList(list:List<Account>){
        Repository.setAllAccounts(list)
    }
}