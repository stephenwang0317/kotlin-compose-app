package com.example.homework.viewmodel

import android.util.Log
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.homework.model.entity.baiduhot.BaiduHotModel
import com.example.homework.model.entity.baiduhot.Content
import com.example.homework.model.entity.baiduhot.TopContent
import com.example.homework.model.service.HotService

class HotViewModel {
    private val hotService = HotService.instance()

    var loading by mutableStateOf(false)

    var baiduHotModel: BaiduHotModel? by mutableStateOf(null)
    var contents = mutableListOf<Content>()
    var topContent = mutableListOf<TopContent>()

    suspend fun getData() {
        loading = true

        val res = hotService.getHot()
        if (res.success) {
            baiduHotModel = res
            contents.addAll(res.data.cards[0].content)
//            topContent = res.data.cards[0].topContent as MutableList<TopContent>
            topContent.addAll(res.data.cards[0].topContent)
        }


        loading = false
    }


}