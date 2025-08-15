package com.example.taskmanagementappusingroomdatabase.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Attachment"
)
data class Attachment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val filePath: String,
    val taskId: Int
)
