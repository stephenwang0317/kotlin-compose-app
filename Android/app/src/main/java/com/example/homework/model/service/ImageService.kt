package com.example.homework.model.service

import com.example.homework.model.entity.BaseResponse
import com.example.homework.network.Network
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ImageService {

//  @Headers("Content-Type: multipart/form-data")
    @POST("avatar/upload/{usr_id}")
    suspend fun uploadImage(
        @Body body: RequestBody,
        @Path("usr_id") usr_id: Int
    ): BaseResponse

    companion object {
        fun instance(): ImageService {
            return Network.createService(ImageService::class.java)
        }
    }

}