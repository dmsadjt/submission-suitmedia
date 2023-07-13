package com.example.suitmedieatestapplication.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.suitmediatestapplication.data.UserPreference
import com.example.suitmediatestapplication.data.di.Injection

import com.example.suitmediatestapplication.ui.palindrome.PalindromeViewModel
import com.example.suitmediatestapplication.ui.user.UserViewModel
import com.example.suitmediatestapplication.ui.welcome.WelcomeViewModel


class ViewModelFactory(
    private val userPreference: UserPreference,
    private val context : Context?
) : ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PalindromeViewModel::class.java)){
            return PalindromeViewModel(userPreference) as T
        } else if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)){
            return WelcomeViewModel(userPreference) as T
        } else if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(userPreference, Injection.provideRepository(context = context!!)) as T
        }
        throw java.lang.IllegalArgumentException("Unknown View Model class: ${modelClass.name}")
    }
}