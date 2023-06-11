package com.example.homework.model.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse(
    @Json(name = "code")
    val code: Int,
    @Json(name = "msg")
    val msg: String
)