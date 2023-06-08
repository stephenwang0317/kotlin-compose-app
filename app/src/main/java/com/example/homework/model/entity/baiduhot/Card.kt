package com.example.homework.model.entity.baiduhot


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Card(
    @Json(name = "component")
    val component: String,
    @Json(name = "content")
    val content: List<Content>,
    @Json(name = "more")
    val more: Int,
    @Json(name = "moreAppUrl")
    val moreAppUrl: String,
    @Json(name = "moreUrl")
    val moreUrl: String,
    @Json(name = "text")
    val text: String,
    @Json(name = "topContent")
    val topContent: List<TopContent>,
    @Json(name = "typeName")
    val typeName: String,
    @Json(name = "updateTime")
    val updateTime: String
)