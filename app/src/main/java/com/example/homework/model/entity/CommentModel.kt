package com.example.homework.model.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentModel(
    @Json(name = "code")
    val code: Int,
    @Json(name = "len")
    val len: Int,
    @Json(name = "list")
    val list: List<CommentItem>,
    @Json(name = "msg")
    val msg: String,
    @Json(name = "type")
    val type: String
)

@JsonClass(generateAdapter = true)
data class CommentItem(
    @Json(name = "cmt_art_id")
    val cmtArtId: Int,
    @Json(name = "cmt_author")
    val cmtAuthor: Int,
    @Json(name = "cmt_author_name")
    val cmtAuthorName: String,
    @Json(name = "cmt_content")
    val cmtContent: String,
    @Json(name = "cmt_id")
    val cmtId: Int? = null,
    @Json(name = "cmt_time")
    val cmtTime: String? = null
)