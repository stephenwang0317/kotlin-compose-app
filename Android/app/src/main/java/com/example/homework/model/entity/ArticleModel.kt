package com.example.homework.model.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//class ArticleModel : ArrayList<ArticleModelItem>()
data class ArticleModel(
    var list: List<ArticleModelItem?>?,
    val code: Int = 0,
    val msg: String = "",
    val len: Int = 0,
    val type: String = ""
)

@JsonClass(generateAdapter = true)
data class ArticleModelItem(
    @Json(name = "art_author")
    val artAuthor: Int? = 0,
    @Json(name = "art_content")
    val artContent: String? = "",
    @Json(name = "art_id")
    val artId: Int? = 0,
    @Json(name = "art_like")
    var artLike: Int = 0,
    @Json(name = "art_summary")
    val artSummary: String? = "",
    @Json(name = "art_time")
    val artTime: String? = "",
    @Json(name = "art_title")
    val artTitle: String? = "",
    @Json(name = "art_author_name")
    val artAuthorName: String? = ""

)