package com.example.homework.model.service

import com.example.homework.model.entity.UserModel
import com.example.homework.network.Network
import retrofit2.http.*

interface UserService {


    //    @FormUrlEncoded
    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("user/login/")
    suspend fun signIn(
        @Body user: UserModel
    ): UserModel

    @GET("user/{user_id}")
    suspend fun getUserById(
        @Path("user_id") user_id: Int
    ): UserModel

    @Headers("Content-Type: application/json;charset=UTF-8")
    @POST("user/")
    suspend fun register(
        @Body user: UserModel
    ): UserModel

    @Headers("Content-Type: application/json;charset=UTF-8")
    @PUT("user/")
    suspend fun changeInfo(
        @Body user: UserModel
    ): UserModel

    companion object {
        fun instance(): UserService {
            return Network.createService(UserService::class.java)
        }
    }
}