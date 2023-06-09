package com.example.homework.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework.model.entity.UserModel
import com.example.homework.model.service.UserInfoManager
import com.example.homework.model.service.UserService
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class UserViewModel(context: Context) : ViewModel() {

    var account by mutableStateOf("")
    var password by mutableStateOf("")
    var username by mutableStateOf("")


    private val userService = UserService.instance()
    private val userInfoManager = UserInfoManager(context = context)

    var userInfo: UserModel? = null
        private set


    var loading by mutableStateOf(false)
        private set

    val logged: Boolean
        get() {
            return userInfo != null
        }

    init {
        viewModelScope.launch {
            val userName = userInfoManager.userName.firstOrNull()
            val userId = userInfoManager.userId.firstOrNull()
            val userAva = userInfoManager.userAva.firstOrNull()

            userInfo = if (userName?.isNotEmpty() == true) {
                UserModel(
                    userId = userId,
                    userName = userName,
                    userAvatar = userAva,
                    userPwd = null
                )
            } else {
                null
            }
        }
    }

    suspend fun login(savePassword: Boolean) {
        loading = true

        val num = try {
            account.toInt()
        } catch (e: NumberFormatException) {
            0
        }
        val tmpUserModel = UserModel(userId = num, userPwd = password)

        Log.i("================Login", "${tmpUserModel.userId} ${tmpUserModel.userPwd}")
        val res = userService.signIn(tmpUserModel)
        Log.i("================Login", res.toString())
        if (res.userName != null) {
            userInfo = res
            if (savePassword) {
                userInfoManager.save(
                    userName = res.userName ?: "",
                    userId = res.userId ?: 0,
                    userAva = res.userAvatar ?: ""
                )
            }
        } else {

        }
        password = ""
        account = ""
        loading = false
    }

    suspend fun getUserById() {
        val res = userService.getUserById(user_id = account.toInt())
        Log.i("=====================", res.toString())
    }

    suspend fun register(savePassword: Boolean) {
        loading = true
        val tmpUserModel = UserModel(userName = username, userPwd = password)
        val res = userService.register(tmpUserModel)
        Log.i("==============Register", res.toString())
        if (res.userName != null) {
            userInfo = res
            if (savePassword) {
                userInfoManager.save(
                    userName = res.userName ?: "",
                    userId = res.userId ?: 0,
                    userAva = res.userAvatar ?: ""
                )
            }
        } else {

        }
        loading = false
    }

    suspend fun changeInfo(savePassword: Boolean) {
        loading = true

        userInfoManager.clear()
        val temp = UserModel(
            userId = userInfo?.userId,
            userAvatar = userInfo?.userAvatar,
            userPwd = password,
            userName = username
        )
        val res = userService.changeInfo(temp)
        if (res.userName != null) {
            userInfo = res
            if (savePassword) {
                userInfoManager.save(
                    userName = res.userName ?: "",
                    userId = res.userId ?: 0,
                    userAva = res.userAvatar ?: ""
                )
            }
        }

        username = ""
        password = ""
        loading = false
    }

    suspend fun logout() {
        loading = true

        userInfoManager.clear()
        userInfo = null
        password = ""
        account = ""
        username = ""

        loading = false
    }
}