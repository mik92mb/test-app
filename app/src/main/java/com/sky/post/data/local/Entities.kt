package com.sky.post.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity (tableName = "post")
data class PostEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "userId")
    val userId: Int,

    @ColumnInfo(name = "title")
    val title : String,

    @ColumnInfo(name = "body")
    val body: String


)


@Entity (tableName = "comment", primaryKeys = ["id","postId"])
data class CommentEntity(

    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "postId")
    val postId : Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "body")
    val body: String


)
