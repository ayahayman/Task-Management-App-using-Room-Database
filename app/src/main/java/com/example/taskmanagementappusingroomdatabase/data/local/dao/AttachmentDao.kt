package com.example.taskmanagementappusingroomdatabase.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Attachment
import com.example.taskmanagementappusingroomdatabase.data.local.entities.Task

@Dao
interface AttachmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttachment(attachment: Attachment): Long

    @Query("DELETE FROM Attachment")
    suspend fun clearAllAttachments()
}