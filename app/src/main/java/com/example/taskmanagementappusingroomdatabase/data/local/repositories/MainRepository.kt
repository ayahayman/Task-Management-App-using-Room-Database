package com.example.taskmanagementappusingroomdatabase.data.local.repositories

import com.example.taskmanagementappusingroomdatabase.data.local.dao.AttachmentDao
import com.example.taskmanagementappusingroomdatabase.data.local.dao.ProjectDao
import com.example.taskmanagementappusingroomdatabase.data.local.dao.TaskDao
import com.example.taskmanagementappusingroomdatabase.data.local.dao.UserDao
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Attachment
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Project
import com.example.taskmanagementappusingroomdatabase.data.local.entities.ProjectWithTasks
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Task
import com.example.taskmanagementappusingroomdatabase.data.local.entities.TaskWithAttachments
import com.example.taskmanagementappusingroomdatabase.data.local.entities.User
import com.example.taskmanagementappusingroomdatabase.data.local.entities.UserWithProjects
import kotlinx.coroutines.flow.Flow

class MainRepository(
    private val userDao: UserDao,
    private val projectDao: ProjectDao,
    private val taskDao: TaskDao,
    private val attachmentDao: AttachmentDao
) {
    suspend fun insertUser(user: User): Int = userDao.insertUser(user).toInt()

    suspend fun insertProject(project: Project): Int = projectDao.insertProject(project).toInt()

    suspend fun insertTask(task: Task): Int = taskDao.insertTask(task).toInt()

    suspend fun insertAttachment(attachment: Attachment): Int = attachmentDao.insertAttachment(attachment).toInt()
    suspend fun getAllProjectsOnce(): List<Project> = projectDao.getAllProjectsOnce()

    fun getAllProjectsFlow(): Flow<List<Project>> = projectDao.getAllProjectsFlow()

    fun getUsersWithProjects(): Flow<List<UserWithProjects>> = userDao.getUsersWithProjects()

    fun getProjectsWithTasks(projectId: Int): Flow<List<ProjectWithTasks>> = projectDao.getProjectWithTasks(projectId)

    fun getTasksWithAttachments(): Flow<List<TaskWithAttachments>> = taskDao.getTaskWithAttachments()

    suspend fun clearAll() {
        userDao.clearAllUsers()
        projectDao.clearAllProjects()
        taskDao.clearAllTasks()
        attachmentDao.clearAllAttachments()
    }

}