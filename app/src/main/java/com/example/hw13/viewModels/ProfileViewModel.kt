package com.example.hw13.viewModels

import androidx.lifecycle.ViewModel
import com.example.hw13.repository.Repository

class ProfileViewModel:ViewModel() {

    var editProfileInfoFlag= Repository.editProfileInfoFlag
}