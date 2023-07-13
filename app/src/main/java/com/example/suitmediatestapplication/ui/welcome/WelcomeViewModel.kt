package com.example.suitmediatestapplication.ui.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.suitmediatestapplication.data.User
import com.example.suitmediatestapplication.data.UserPreference

class WelcomeViewModel(private val userPreference: UserPreference) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user : LiveData<User> = _user

    init {
        getUser()
    }

    private fun getUser()  {
        _user.value = userPreference.getUser()
    }
}