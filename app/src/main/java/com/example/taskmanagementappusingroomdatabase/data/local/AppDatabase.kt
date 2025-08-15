package com.example.taskmanagementappusingroomdatabase.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskmanagementappusingroomdatabase.data.local.dao.AttachmentDao
import com.example.taskmanagementappusingroomdatabase.data.local.dao.ProjectDao
import com.example.taskmanagementappusingroomdatabase.data.local.dao.TaskDao
import com.example.taskmanagementappusingroomdatabase.data.local.dao.UserDao
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Attachment
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Project
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Task
import com.example.taskmanagementappusingroomdatabase.data.local.entities.User

@Database(entities = [User::class, Project::class, Task::class, Attachment::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun projectDao(): ProjectDao
    abstract fun taskDao(): TaskDao
    abstract fun attachmentDao(): AttachmentDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context):
                AppDatabase = INSTANCE ?: synchronized(this){
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "TaskManagementDB"
            ).build()
    }
}