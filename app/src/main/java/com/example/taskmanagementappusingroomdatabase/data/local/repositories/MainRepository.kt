package com.example.taskmanagementappusingroomdatabase.data.local.repositories

import com.example.taskmanagementappusingroomdatabase.data.local.dao.AttachmentDao
import com.example.taskmanagementappusingroomdatabase.data.local.dao.ProjectDao
import com.example.taskmanagementappusingroomdatabase.data.local.dao.TaskDao
import com.example.taskmanagementappusingroomdatabase.data.local.dao.UserDao
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Attachment
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Project
import com.example.taskmanagementappusingroomdatabase.data.local.entities.ProjectWithTask
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Task
import com.example.taskmanagementappusingroomdatabase.data.local.entities.User
import com.example.taskmanagementappusingroomdatabase.data.local.entities.UserWithProject
import kotlinx.coroutines.flow.Flow

class MainRepository(
    private val userDao: UserDao,
    private val projectDao: ProjectDao,
    private val taskDao: TaskDao,
    private val attachmentDao: AttachmentDao
) {
    suspend fun addUser(user: User): Long = userDao.insertUser(user)

    suspend fun addProject(project: Project): Long = projectDao.insertProject(project)

    suspend fun addTask(task: Task): Long = taskDao.insertTask(task)

    suspend fun addAttachment(attachment: Attachment): Long = attachmentDao.insertAttachment(attachment)

    fun getUsersWithProjects(): Flow<List<UserWithProject>> = userDao.getUsersWithProjects()

    fun getProjectsWithTasks(): FLow<List<ProjectWithTask>> = projectDao.getProjectWithTasks()

    suspend fun clearAll() {
        userDao.clearAllUsers()
        projectDao.clearAllProjects()
        taskDao.clearAllTasks()
        attachmentDao.clearAllAttachments()
    }

}