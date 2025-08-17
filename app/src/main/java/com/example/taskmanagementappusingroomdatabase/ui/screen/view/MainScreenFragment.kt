package com.example.taskmanagementappusingroomdatabase.ui.screen.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.taskmanagementappusingroomdatabase.ui.screen.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainScreenFragment: Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private val TAG = "ViewModelTest"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Observe LiveData (all projects)
        viewModel.allProjects.observe(viewLifecycleOwner) { projects ->
            Log.d(TAG, "LiveData - All projects (${projects.size}):")
            projects.forEach { Log.d(TAG, "- ${it.title} (ID: ${it.projectId})") }
        }

        // 2. Collect Flow for specific project's tasks
        lifecycleScope.launch {
            viewModel.projectsWithTasksFlow(projectId = 1).collect { projectsWithTasks ->
                Log.d(TAG, "Flow - Projects with tasks:")
                projectsWithTasks.forEach { projectWithTasks ->
                    Log.d(TAG, "Project: ${projectWithTasks.project.title}")
                    projectWithTasks.tasks.forEach { task ->
                        Log.d(TAG, "  - Task: ${task.description}")
                    }
                }
            }
        }
    }
}