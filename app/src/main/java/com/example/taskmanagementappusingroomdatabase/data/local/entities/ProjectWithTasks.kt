package com.example.taskmanagementappusingroomdatabase.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(
    primaryKeys = ["projectId", "taskId"]
)
data class ProjectTaskCrossRef(
    val projectId: Int,
    val taskId: Int
)

data class ProjectWithTasks(
    @Embedded
    val project: Project,
    @Relation(
        parentColumn = "projectId",
        entityColumn = "taskId",
        associateBy = Junction(
            value = ProjectTaskCrossRef::class,
            parentColumn = "projectId",
            entityColumn = "taskId"
        )
    )
    val tasks: List<Task>
)

data class TaskWithProjects(
    @Embedded
    val task: Task,
    @Relation(
        parentColumn = "taskId",
        entityColumn = "projectId",
        associateBy = Junction(
            value = ProjectTaskCrossRef::class,
            parentColumn = "taskId",
            entityColumn = "projectId"
        )
    )
    val projects: List<Project>
)
