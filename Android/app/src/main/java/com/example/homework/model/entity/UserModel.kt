package com.example.homework.model.entity


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserModel(
    @Json(name = "user_avater")
    var userAvatar: String? = null,
    @Json(name = "user_id")
    var userId: Int? = null,

    @Json(name = "user_name")
    var userName: String? = null,

    @Json(name = "user_pwd")
    var userPwd: String? = null
)