package com.example.suitmediatestapplication.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmediatestapplication.data.UserPreference
import com.example.suitmediatestapplication.data.database.UserRepository
import com.example.suitmediatestapplication.data.remote.DataItem

class UserViewModel(private val userPreference: UserPreference, userRepository: UserRepository) : ViewModel() {
    val listUser : LiveData<PagingData<DataItem>> = userRepository.getUser().cachedIn(viewModelScope)

    var onUserSaved : OnUserSaved ?= null

    fun setSelectedUser(name: String) {
        userPreference.setSelectedUser(name)
        onUserSaved?.redirectToSecondPage()
    }

    interface OnUserSaved{
        fun redirectToSecondPage()
    }
}