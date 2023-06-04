package com.example.homework.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.homework.model.entity.ArticleModelItem
import com.example.homework.model.entity.UserModel
import com.example.homework.model.service.ArticleService

class NewArticleItemViewModel {

    var title by mutableStateOf("")
    var content by mutableStateOf("")

    private val articleService = ArticleService.instance()

    var newArticleIfo: ArticleModelItem? = null
        private set

    var loading by mutableStateOf(false)
    private set

    suspend fun postArticle(userModel: UserModel){
        loading = true

        val tempArticleItem = ArticleModelItem(
            artAuthor = userModel.userId,
            artContent = content,
            artLike = 0,
            artTitle = title,
            artAuthorName = userModel.userName
        )

        val res = articleService.createArticle(tempArticleItem)
        newArticleIfo = res
        Log.i("==================", res.toString())

        loading = false
    }
}