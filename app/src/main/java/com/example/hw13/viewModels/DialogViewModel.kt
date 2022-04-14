package com.example.hw13.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.hw13.repository.Repository


class DialogViewModel (App: Application): AndroidViewModel(App){
    fun delete(){
        Repository.delete()
    }

    init {
        Repository.initDB(App.applicationContext)
    }

}