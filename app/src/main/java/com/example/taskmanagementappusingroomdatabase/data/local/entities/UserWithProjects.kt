package com.example.taskmanagementappusingroomdatabase.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithProject(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "ownerId"
    )
    val projects: List<Project>
)
