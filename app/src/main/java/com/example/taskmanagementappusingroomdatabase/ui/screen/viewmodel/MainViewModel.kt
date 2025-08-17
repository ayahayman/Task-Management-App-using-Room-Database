package com.example.taskmanagementappusingroomdatabase.ui.screen.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.taskmanagementappusingroomdatabase.data.local.AppDatabase
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Project
import com.example.taskmanagementappusingroomdatabase.data.local.entities.ProjectWithTasks
import com.example.taskmanagementappusingroomdatabase.data.local.entities.TaskWithAttachments
import com.example.taskmanagementappusingroomdatabase.data.local.entities.UserWithProjects
import com.example.taskmanagementappusingroomdatabase.data.local.repositories.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MainRepository

    // LiveData for all projects
    val allProjects: LiveData<List<Project>>

    init {
        val db = AppDatabase.getDatabase(application)
        repository = MainRepository(
            db.userDao(),
            db.projectDao(),
            db.taskDao(),
            db.attachmentDao()
        )

        // Convert Flow to LiveData
        allProjects = repository.getAllProjectsFlow().asLiveData()
    }

    // Flow for projects with tasks (takes projectId as parameter)
    fun projectsWithTasksFlow(projectId: Int): Flow<List<ProjectWithTasks>> {
        return repository.getProjectsWithTasks(projectId)
    }
}
