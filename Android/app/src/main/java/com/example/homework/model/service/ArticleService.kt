package com.example.homework.model.service

import com.example.homework.model.entity.ArticleModel
import com.example.homework.model.entity.ArticleModelItem
import com.example.homework.model.entity.BaseResponse
import com.example.homework.network.Network
import retrofit2.http.*


interface ArticleService {
    @GET("article")
    suspend fun getAllArticle(): ArticleModel

    @GET("article/page/{page_num}")
    suspend fun getPageArticle(
        @Path("page_num") page_num: Int
    ): ArticleModel

    @DELETE("article/{art_id}")
    suspend fun deleteArticle(
        @Path("art_id") art_id: Int
    ): BaseResponse

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("article")
    suspend fun createArticle(
        @Body article: ArticleModelItem
    ): ArticleModelItem

    @GET("article/{art_id}")
    suspend fun getArtById(
        @Path("art_id") art_id: Int
    ): ArticleModelItem

    @GET("article/user/{user_id}")
    suspend fun getUserArticle(
        @Path("user_id") user_id: Int
    ): ArticleModel

    @GET("article/like/{user_id}")
    suspend fun getUserLike(
        @Path("user_id") user_id: Int
    ): ArticleModel

    @PUT("article/{user_id}/{art_id}")
    suspend fun like(
        @Path("user_id") user_id: Int,
        @Path("art_id") art_id: Int
    ): BaseResponse

    @DELETE("article/{user_id}/{art_id}")
    suspend fun dislike(
        @Path("user_id") user_id: Int,
        @Path("art_id") art_id: Int
    ): BaseResponse

    @GET("article/{user_id}/{art_id}")
    suspend fun checkIfLike(
        @Path("user_id") user_id: Int,
        @Path("art_id") art_id: Int
    ): BaseResponse

    companion object {
        fun instance(): ArticleService {
            return Network.createService(ArticleService::class.java)
        }
    }
}