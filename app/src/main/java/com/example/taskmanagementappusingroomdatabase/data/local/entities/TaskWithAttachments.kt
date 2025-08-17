package com.example.taskmanagementappusingroomdatabase.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithAttachments(
    @Embedded
    val task: Task,
    @Relation(
        parentColumn = "taskId",
        entityColumn = "taskId"
    )
    val attachments: List<Attachment>
)
