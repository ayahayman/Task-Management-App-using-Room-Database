package com.example.taskmanagementappusingroomdatabase.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Project
import com.example.taskmanagementappusingroomdatabase.data.local.entities.ProjectWithTasks
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Task
import com.example.taskmanagementappusingroomdatabase.data.local.entities.UserWithProject
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProject(project: Project): Long

    @Query("SELECT * FROM Project")
    suspend fun getAllProjectsOnce(): List<Project>

    @Query("SELECT * FROM Project")
    fun getAllProjectsFlow(): Flow<List<Project>>

    @Transaction
    @Query("SELECT * FROM Project")
    fun getProjectWithTasks(): Flow<List<ProjectWithTasks>>

    @Query("DELETE FROM Project")
    suspend fun clearAllProjects()
}