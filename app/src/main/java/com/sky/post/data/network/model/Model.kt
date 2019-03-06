package com.sky.post.data.network.model

import com.google.gson.annotations.SerializedName
import com.sky.post.data.local.CommentEntity
import com.sky.post.data.local.PostEntity

// RISPECCHIA IL MODELLO DEL JSON ==> DIFFERISCE DAL MODELLO CONCETTUALE!!


data class Post(

    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
) {
    fun toPostEntity() = PostEntity(id, userId, title, body)

}

data class Comment(

    @SerializedName("id")
    val id: Int,
    @SerializedName("postId")
    val postId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("body")
    val body: String

) {
    fun toCommentsEntity() = CommentEntity(id, postId, name, email, body)
}
