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
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ProjectTaskCrossRef::class,
            parentColumn = "projectId",
            entityColumn = "taskId"
        )
    )
    val tasks: List<Task>
)

data class TaskWithProject(
    @Embedded
    val task: Task,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TaskWithProject::class,
            parentColumn = "taskId",
            entityColumn = "projectId"
        )
    )
    val projects: List<Project>
)
