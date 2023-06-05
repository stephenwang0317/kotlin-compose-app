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

    suspend fun getArtById(art_id: Int) {
        loading = true
        delay(500)
        val res = articleService.getArtById(art_id)
//        Log.i("++++++++++++", res.toString())
        artItem = res
        loading = false
    }

    suspend fun like(user_id: Int) {
        loading = true
//        delay(500)
        artItem.artLike += 1
        loading = false
    }

    suspend fun dislike(user_id: Int){
        loading = true
//        delay(500)
        artItem.artLike -= 1
        loading = false
    }
}