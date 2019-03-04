package com.sky.post.network.model

import com.google.gson.annotations.SerializedName

// RISPECCHIA IL MODELLO DEL JSON ==> DIFFERISCE DAL MODELLO CONCETTUALE!!


data class Post(

    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title : String,
    @SerializedName("body")
    val body: String
)


data class Comment(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("body")
    val body: String
)
