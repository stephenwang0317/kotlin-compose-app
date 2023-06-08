package com.example.homework.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.homework.model.entity.ArticleModelItem
import com.example.homework.model.service.ArticleService
import kotlinx.coroutines.delay

class ArticleItemViewModel {

    private val articleService = ArticleService.instance()

    var artItem: ArticleModelItem by mutableStateOf(ArticleModelItem())

    var loading by mutableStateOf(true)
        private set

    // 0: not sure/loading
    // 1: like
    // -1: dislike
    var isLike: Int by mutableStateOf(0)

    suspend fun getArtById(art_id: Int) {
        loading = true
        delay(500)
        val res = articleService.getArtById(art_id)
        artItem = res
        loading = false
    }

    suspend fun checkIfLike(art_id: Int, user_id: Int) {
        isLike = 0
        delay(1000)
        val res = articleService.checkIfLike(art_id = art_id, user_id = user_id)
        if (res.code == 2) {
            if (res.msg == "yes") isLike = 1;
            else if (res.msg == "no") isLike = -1;
        }
    }

    suspend fun like(user_id: Int, art_id: Int) {
        isLike = 0
//        delay(500)
        val res = articleService.like(user_id = user_id, art_id = art_id)
        if (res.code == 0) {
            artItem.artLike += 1
        }
        isLike = 1
    }

    suspend fun dislike(user_id: Int, art_id: Int) {
        isLike = 0
//        delay(500)
        val res = articleService.dislike(user_id = user_id, art_id = art_id)
        if (res.code == 0) {
            artItem.artLike -= 1
        }
        isLike = -1
    }
}