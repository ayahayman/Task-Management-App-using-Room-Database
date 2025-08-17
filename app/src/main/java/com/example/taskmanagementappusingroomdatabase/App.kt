package com.example.taskmanagementappusingroomdatabase

import android.app.Application
import com.example.taskmanagementappusingroomdatabase.data.local.AppDatabase
import com.example.taskmanagementappusingroomdatabase.data.local.repositories.MainRepository

class MyApp : Application() {
    // database instance
    val database by lazy { AppDatabase.getDatabase(this) }

    // repository instance
    val repository by lazy { MainRepository(database.userDao(), database.projectDao(), database.taskDao(), database.attachmentDao()) }
}
