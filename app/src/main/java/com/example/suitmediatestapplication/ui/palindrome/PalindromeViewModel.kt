package com.example.suitmediatestapplication.ui.palindrome

import androidx.lifecycle.ViewModel
import com.example.suitmediatestapplication.data.UserPreference

class PalindromeViewModel(private val userPreference: UserPreference) : ViewModel() {


    var palindromeCallback : PalindromeCallback? = null

    fun checkPalindrome(text : String) {
        val reverse = text.reversed()
        if(text == reverse){
            palindromeCallback?.palindromeCheck("isPalindrome")
        } else {
            palindromeCallback?.palindromeCheck("not palindrome")
        }
    }

    fun saveUser(user: String) {
        userPreference.setName(user)
    }

    interface PalindromeCallback{
        fun palindromeCheck(message: String)
    }
}