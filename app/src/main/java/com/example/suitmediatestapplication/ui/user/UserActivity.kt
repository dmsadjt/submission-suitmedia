package com.example.suitmediatestapplication.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediatestapplication.R
import com.example.suitmediatestapplication.data.UserPreference
import com.example.suitmediatestapplication.data.remote.DataItem
import com.example.suitmediatestapplication.databinding.ActivityUserBinding
import com.example.suitmediatestapplication.ui.welcome.WelcomeActivity
import com.example.suitmedieatestapplication.ui.ViewModelFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class UserActivity : AppCompatActivity(), UserViewModel.OnUserSaved {

    private lateinit var binding : ActivityUserBinding
    private val userViewModel by viewModels<UserViewModel> {
        ViewModelFactory(userPreference = UserPreference(this), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        supportActionBar?.title = "Third Screen"
        userViewModel.onUserSaved = this

        userViewModel.listUser.observe(this) {
            getUserData()
        }
    }

    private fun getUserData(){
        val adapter = UserAdapter()
        binding.rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        userViewModel.listUser.observe(this) {
            adapter.submitData(lifecycle, it)
            adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                override fun onItemClicked(fullName: String) {
                    userViewModel.setSelectedUser(fullName)
                }
            })
        }
    }

    override fun redirectToSecondPage() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }
}