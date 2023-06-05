package com.example.homework.model.service

import com.example.homework.model.entity.ArticleModel
import com.example.homework.model.entity.ArticleModelItem
import com.example.homework.network.Network
import retrofit2.http.*


interface ArticleService {
    @GET("article")
    suspend fun getAllArticle(): ArticleModel

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("article")
    suspend fun createArticle(
        @Body article: ArticleModelItem
    ): ArticleModelItem

    @GET("article/{art_id}")
    suspend fun getArtById(
        @Path("art_id") art_id:Int
    ): ArticleModelItem

    companion object {
        fun instance(): ArticleService {
            return Network.createService(ArticleService::class.java)
        }
    }
}