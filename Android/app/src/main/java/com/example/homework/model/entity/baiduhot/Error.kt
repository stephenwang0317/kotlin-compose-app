package com.example.homework.model.entity.baiduhot


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Error(
    @Json(name = "code")
    val code: Int,
    @Json(name = "message")
    val message: String
)