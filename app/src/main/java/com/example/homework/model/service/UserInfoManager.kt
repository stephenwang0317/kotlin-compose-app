package com.example.homework.model.service

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserInfoManager(private val context: Context) {

    companion object {
        private val Context.userStore: DataStore<Preferences> by preferencesDataStore("user_store")

        val LOGGED = booleanPreferencesKey("LOGGED")
        val USERNAME = stringPreferencesKey("USERNAME")
        val USERID = intPreferencesKey("USERID")
        val USERAVA = stringPreferencesKey("USERAVA")
//        val USERPWD = stringPreferencesKey("")

    }

    val logged: Flow<Boolean> = context.userStore.data.map { it[LOGGED] ?: false }
    val userName: Flow<String> = context.userStore.data.map { it[USERNAME] ?: "" }
    val userId: Flow<Int> = context.userStore.data.map { it[USERID] ?: 0 }
    val userAva: Flow<String> = context.userStore.data.map { it[USERAVA] ?: "" }


    suspend fun save(userName: String, userId: Int, userAva: String) {
        context.userStore.edit {
            it[LOGGED] = userName.isNotEmpty()
            it[USERNAME] = userName
            it[USERID] = userId
            it[USERAVA] = userAva
        }
    }

    suspend fun clear() {
        context.userStore.edit {
            it[LOGGED] = false
            it[USERNAME] = ""
            it[USERID] = 0
            it[USERAVA] = ""
        }
    }

}