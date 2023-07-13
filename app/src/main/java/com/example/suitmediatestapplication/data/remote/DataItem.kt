package com.example.suitmediatestapplication.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class DataItem(

    @field:SerializedName("last_name")
    val lastName: String? = null,

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int? = 1,

    @field:SerializedName("avatar")
    val avatar: String? = null,

    @field:SerializedName("first_name")
    val firstName: String? = null,

    @field:SerializedName("email")
    val email: String? = null
)
