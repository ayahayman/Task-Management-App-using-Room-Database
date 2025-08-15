package com.example.taskmanagementappusingroomdatabase.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.taskmanagementappusingroomdatabase.data.local.entities.User
import com.example.taskmanagementappusingroomdatabase.data.local.entities.UserWithProject
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User): Long

    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersWithProjects(): Flow<List<UserWithProject>>

    @Query("DELETE FROM User")
    suspend fun clearAllUsers()
}