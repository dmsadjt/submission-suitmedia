package com.example.suitmediatestapplication.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.suitmediatestapplication.data.remote.DataItem

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUser() : PagingSource<Int, DataItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = DataItem::class)
    suspend fun insertUser(story: List<DataItem?>?)

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}