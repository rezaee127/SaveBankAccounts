package com.example.hw13.viewModels

import androidx.lifecycle.ViewModel
import com.example.hw13.repository.Repository


class DialogViewModel : ViewModel(){
    fun delete(){
        Repository.delete()
    }

}