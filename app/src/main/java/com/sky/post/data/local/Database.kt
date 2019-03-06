package com.sky.post.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database (entities = [PostEntity::class, CommentEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val entityDao : EntityDao
}