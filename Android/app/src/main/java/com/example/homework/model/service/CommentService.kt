package com.example.homework.model.service

import com.example.homework.model.entity.BaseResponse
import com.example.homework.model.entity.CommentItem
import com.example.homework.model.entity.CommentModel
import com.example.homework.network.Network
import retrofit2.http.*

interface CommentService {

    @GET("comment/user/{user_id}")
    suspend fun getCommentOfUser(
        @Path("user_id") user_id: Int
    ): CommentModel

    @GET("comment/article/{art_id}")
    suspend fun getCommentOfArticle(
        @Path("art_id") art_id: Int
    ): CommentModel

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("comment")
    suspend fun postComment(
        @Body comment: CommentItem
    ): CommentItem

    companion object {
        fun instance(): CommentService {
            return Network.createService(CommentService::class.java)
        }
    }
}