package com.example.suitmediatestapplication.data.di

import android.content.Context
import com.example.suitmediatestapplication.data.database.UserDatabase
import com.example.suitmediatestapplication.data.database.UserRepository
import com.example.suitmediatestapplication.data.remote.ApiConfig

object Injection {
    fun provideRepository(context : Context) : UserRepository {
        var userDatabase = UserDatabase.getDatabase(context)
        val apiService = ApiConfig().getApiService()
        return UserRepository(userDatabase, apiService)
    }
}