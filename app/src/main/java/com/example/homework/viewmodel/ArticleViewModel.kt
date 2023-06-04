package com.example.homework.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.homework.model.entity.ArticleModel
import com.example.homework.model.service.ArticleService

class ArticleViewModel {

    private val articleService = ArticleService.instance()

    var articleModel: ArticleModel? = null
        private set

    var loading by mutableStateOf(false)
        private set

    suspend fun getAllArticle() {
        loading = true

        val res = articleService.getAllArticle()
        articleModel = res

        loading = false
    }
}