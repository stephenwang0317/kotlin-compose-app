package com.example.homework.compositionLocal

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import com.example.homework.viewmodel.UserViewModel

val LocalUserViewModel =
    compositionLocalOf<UserViewModel> { error("User View Model Context Not Found") }

val LocalNavController =
    compositionLocalOf<NavHostController> { error("NavHostController Context Not Found") }