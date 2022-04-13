package com.example.hw13.viewModels

import androidx.lifecycle.ViewModel
import com.example.hw13.models.Account
import com.example.hw13.repository.Repository

class CreateAccountViewModel:ViewModel() {
    var listOfAccount=ArrayList<Account>()

    fun setList(list:List<Account>){
        Repository.setAllAccounts(list)
    }
}