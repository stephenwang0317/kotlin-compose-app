package com.example.homework.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.integerArrayResource
import com.example.homework.model.entity.CommentItem
import com.example.homework.model.entity.UserModel
import com.example.homework.model.service.CommentService
import java.lang.Exception

class CommentViewModel {

    private val commentService = CommentService.instance()

    val comList = mutableListOf<CommentItem>()
    var content by mutableStateOf("")

    var loading by mutableStateOf(false)
    var postLoading by mutableStateOf(false)

    suspend fun getCommentOfUser(user_id: Int) {
        loading = true

        val res = commentService.getCommentOfUser(user_id = user_id)

        if (res.code == 0) {
            comList.addAll(res.list)
        }

        loading = false
    }

    suspend fun getCommentOfArticle(art_id: Int) {
        loading = true

        try {
            val res = commentService.getCommentOfArticle(art_id = art_id)
            Log.i("ppppppppppppppp", res.toString())
            if (res.code == 0) {
                comList.addAll(res.list)
            }
        } catch (e: Exception) {

        }


        loading = false
    }

    suspend fun postComment(art_id: Int, userModel: UserModel) {
        postLoading = true

        val temp = CommentItem(
            cmtArtId = art_id,
            cmtAuthor = userModel.userId ?: 0,
            cmtAuthorName = userModel.userName ?: "",
            cmtContent = content,
        )
        val res = commentService.postComment(temp)
//        Log.i("=================post",res.toString())
        if (res.cmtId != 0) {
            loading = true
            comList.add(res)
            loading = false
        }
        postLoading = false
    }
}