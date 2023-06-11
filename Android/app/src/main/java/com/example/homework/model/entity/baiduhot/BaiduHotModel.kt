package com.example.homework.model.entity.baiduhot


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaiduHotModel(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "error")
    val error: Error,
    @Json(name = "success")
    val success: Boolean
)