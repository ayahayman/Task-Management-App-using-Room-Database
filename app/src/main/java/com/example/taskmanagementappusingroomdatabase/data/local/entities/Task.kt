package com.example.taskmanagementappusingroomdatabase.data.local.entities

import android.app.ActivityManager.TaskDescription
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Task",
    indices = [Index(value = ["projectId"])]
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String,
    val projectId: Int
)
