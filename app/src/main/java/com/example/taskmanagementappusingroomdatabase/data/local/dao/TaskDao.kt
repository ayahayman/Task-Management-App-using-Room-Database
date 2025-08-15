package com.example.taskmanagementappusingroomdatabase.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task): Long

    @Query("DELETE FROM Task")
    suspend fun clearAllTasks()
}