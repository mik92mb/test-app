package com.sky.post.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface EntityDao {

    @Query("SELECT * FROM post")
    fun getAllPosts(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPost(post: PostEntity)

    @Query("SELECT * FROM comment WHERE postId= :postId")
    fun getComments(postId: Int): List<CommentEntity>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertComment(commentEntity: CommentEntity)
}