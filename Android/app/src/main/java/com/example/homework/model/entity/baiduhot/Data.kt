package com.example.homework.model.entity.baiduhot


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "cards")
    val cards: List<Card>,
    @Json(name = "curBoardName")
    val curBoardName: String,
    @Json(name = "logid")
    val logid: String,
    @Json(name = "platform")
    val platform: String,
    @Json(name = "tabBoard")
    val tabBoard: List<TabBoard>,
    @Json(name = "tag")
    val tag: List<Any>
)