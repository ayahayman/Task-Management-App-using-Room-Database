package com.example.taskmanagementappusingroomdatabase.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Project
import com.example.taskmanagementappusingroomdatabase.data.local.entities.ProjectWithTasks
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Task
import com.example.taskmanagementappusingroomdatabase.data.local.entities.UserWithProjects
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project): Long

    @Query("SELECT * FROM Project")
    suspend fun getAllProjectsOnce(): List<Project> // Suspend DAO returns data once (snapshot).

    @Query("SELECT * FROM Project")
    fun getAllProjectsFlow(): Flow<List<Project>> // Flow DAO observes changes and emits updates whenever underlying data changes.

    @Transaction
    @Query("SELECT * FROM Project WHERE projectId = :projectId")
    fun getProjectWithTasks(projectId: Int): Flow<List<ProjectWithTasks>>

    // 1. Standard @Query version
    @Query("""
        SELECT p.* FROM Project p
        JOIN Task t ON t.projectId = p.projectId
        GROUP BY p.projectId
        HAVING COUNT(t.taskId) > 3
    """)
    suspend fun getProjectsWithMoreThan3Tasks(): List<Project>

    // 2. RawQuery version
    @RawQuery
    suspend fun getProjectsWithMoreThan3TasksRaw(query: SupportSQLiteQuery): List<Project>

    companion object {
        // Raw query SQL string
        const val RAW_QUERY = """
            SELECT p.* FROM Project p
            JOIN Task t ON t.projectId = p.projectId
            GROUP BY p.projectId
            HAVING COUNT(t.taskId) > 3
        """
    }

    @Query("DELETE FROM Project")
    suspend fun clearAllProjects()
}