package com.example.taskmanagementappusingroomdatabase.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Project"
)
data class Project(
    @PrimaryKey(autoGenerate = true)
    val projectId: Int = 0,
    val title: String,
    val ownerId: Int
)
