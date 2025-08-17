package com.example.taskmanagementappusingroomdatabase.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithProjects(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "ownerId"
    )
    val projects: List<Project>
)
