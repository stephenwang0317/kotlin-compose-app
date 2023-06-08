package com.example.homework.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.homework.model.entity.ArticleModel
import com.example.homework.model.entity.ArticleModelItem
import com.example.homework.model.service.ArticleService
import java.util.*

class ArticleViewModel {

    private val articleService = ArticleService.instance()

    val tmpList = mutableListOf<ArticleModelItem>()

    var loading by mutableStateOf(false)
        private set

    suspend fun getAllArticle() {
        loading = true

        val res = articleService.getAllArticle()
//        Log.i("============", tmpList.size.toString())
//        Log.i("============code", res.code.toString())
        if (res.code == 0) {
            tmpList.addAll(res.list ?: Collections.emptyList())
        }
//        Log.i("============", tmpList.size.toString())
        loading = false
    }

}