package com.example.suitmediatestapplication.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/users")
    suspend fun getUsers(
        @Query("page") page : Int? = null,
        @Query("per_page") perPage : Int? = null,
    ) : UserResponse

    @GET("/api/users")
    fun getUsersWithoutPaging(
        @Query("page") page : Int = 1,
        @Query("per_page") perPage : Int = 10,
    ) : Call<UserResponse>
}