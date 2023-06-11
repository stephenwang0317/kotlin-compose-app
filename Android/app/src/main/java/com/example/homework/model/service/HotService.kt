package com.example.homework.model.service

import com.example.homework.model.entity.baiduhot.BaiduHotModel
import com.example.homework.network.Network
import retrofit2.http.GET

interface HotService {

    @GET("https://top.baidu.com/api/board?platform=wise&tab=realtime")
    suspend fun getHot(): BaiduHotModel

    companion object {
        fun instance(): HotService {
            return Network.createService(HotService::class.java)
        }
    }
}