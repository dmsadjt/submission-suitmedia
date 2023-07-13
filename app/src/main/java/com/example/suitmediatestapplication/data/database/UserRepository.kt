package com.example.suitmediatestapplication.data.database

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.suitmediatestapplication.data.paging.UserRemoteMediator
import com.example.suitmediatestapplication.data.remote.ApiService
import com.example.suitmediatestapplication.data.remote.DataItem
import com.example.suitmediatestapplication.data.remote.UserResponse
import retrofit2.Call

class UserRepository(private val userDatabase: UserDatabase, private val apiService: ApiService) {
    fun getUser() : LiveData<PagingData<DataItem>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            remoteMediator = UserRemoteMediator(userDatabase, apiService),
            pagingSourceFactory = {
                userDatabase.userDao().getAllUser()
            }
        ).liveData
    }

    fun getUserWithoutPaging(page: Int = 1, perPage: Int = 10) : Call<UserResponse> {
        return apiService.getUsersWithoutPaging(page,perPage)
    }
}