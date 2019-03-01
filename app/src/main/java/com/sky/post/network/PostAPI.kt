package com.sky.post.network

import com.sky.post.network.model.Comment
import com.sky.post.network.model.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface PostAPI {


    @GET("/posts")
    fun getPost() : Observable<List<Post>>

    @GET("/posts/{id}")
    fun getPostbyId(@Path("id") id: Int) : Observable<Post>

    @GET("/posts/{id}/comments")
    fun getCommentsbyId(@Path("id") id: Int) : Observable<List<Comment>>
}