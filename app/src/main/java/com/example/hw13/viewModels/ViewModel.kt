package com.example.hw13.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.hw13.repository.Repository

class ViewModel (App: Application): AndroidViewModel(App){

    //var numberOfAccount=Repository.numberOfAccount
    var editProfileInfoFlag=Repository.editProfileInfoFlag


    init {
        Repository.initDB(App.applicationContext)

    }

}