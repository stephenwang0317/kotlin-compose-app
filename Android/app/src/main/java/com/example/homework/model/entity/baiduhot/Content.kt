package com.example.homework.model.entity.baiduhot


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Content(
    @Json(name = "appUrl")
    val appUrl: String = "",
    @Json(name = "desc")
    val desc: String = "",
    @Json(name = "expression")
    val expression: String = "",
    @Json(name = "hotChange")
    val hotChange: String = "",
    @Json(name = "hotScore")
    val hotScore: String = "",
    @Json(name = "hotTag")
    val hotTag: String = "",
    @Json(name = "hotTagImg")
    val hotTagImg: String = "",
    @Json(name = "img")
    val img: String = "",
    @Json(name = "index")
    val index: Int = 0,
    @Json(name = "indexUrl")
    val indexUrl: String = "",
    @Json(name = "query")
    val query: String = "",
    @Json(name = "rawUrl")
    val rawUrl: String = "",
    @Json(name = "show")
    val show: List<Any>,
    @Json(name = "url")
    val url: String = "",
    @Json(name = "word")
    val word: String = ""
)