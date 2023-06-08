package com.example.homework.model.entity.baiduhot


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TabBoard(
    @Json(name = "index")
    val index: Int,
    @Json(name = "text")
    val text: String,
    @Json(name = "typeName")
    val typeName: String
)