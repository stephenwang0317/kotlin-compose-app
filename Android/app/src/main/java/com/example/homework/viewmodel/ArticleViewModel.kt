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
            res.list?.forEach {
                if (it != null) tmpList.add(it)
            }
        }
//        Log.i("============", tmpList.size.toString())
        loading = false
    }

    suspend fun getUserArticle(user_id: Int) {
        loading = true

        val res = articleService.getUserArticle(user_id = user_id)
        Log.i(">>>>>>>>>>", res.toString())
        if (res.code == 0) {
            if (res.code == 0) {
                res.list?.forEach {
                    if (it != null) tmpList.add(it)
                }
            }
        }

        loading = false
    }

    suspend fun getUserLike(user_id: Int) {
        loading = true

        val res = articleService.getUserLike(user_id)
        Log.i(">>>>>>>>>>", res.toString())
        if (res.code == 0) {
            if (res.code == 0) {
                res.list?.forEach {
                    if (it != null) tmpList.add(it)
                }
            }
        }

        loading = false
    }

}