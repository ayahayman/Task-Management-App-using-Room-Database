package com.example.taskmanagementappusingroomdatabase.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.taskmanagementappusingroomdatabase.data.local.entities.User
import com.example.taskmanagementappusingroomdatabase.data.local.entities.UserWithProjects
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersWithProjects(): Flow<List<UserWithProjects>>

    @Query("DELETE FROM User")
    suspend fun clearAllUsers()
}