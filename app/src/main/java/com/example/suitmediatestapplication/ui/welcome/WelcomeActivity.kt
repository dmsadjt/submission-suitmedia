package com.example.suitmediatestapplication.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.suitmediatestapplication.R
import com.example.suitmediatestapplication.data.User
import com.example.suitmediatestapplication.data.UserPreference
import com.example.suitmediatestapplication.databinding.ActivityWelcomeBinding
import com.example.suitmediatestapplication.ui.palindrome.PalindromeActivity
import com.example.suitmediatestapplication.ui.user.UserActivity
import com.example.suitmedieatestapplication.ui.ViewModelFactory

class WelcomeActivity : AppCompatActivity() {

    private val welcomeViewModel by viewModels<WelcomeViewModel> {
        ViewModelFactory(userPreference = UserPreference(this), this)
    }
    private lateinit var binding : ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Second Screen"
        welcomeViewModel.user.observe(this){
            setData(it)
        }
        binding.btnChoose.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    fun setData(user : User){
        binding.tvUsername.text = user.name
        binding.tvSelected.text = if(user.selected.isNullOrBlank()) "No user is selected" else user.selected
    }

    override fun onBackPressed() {
        startActivity(Intent(this, PalindromeActivity::class.java))
    }
}