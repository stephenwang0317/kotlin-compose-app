package com.example.homework.component

import android.util.Log
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.homework.Screen

@Composable
fun MyBottomBar(
    navHostController: NavHostController,
) {
    val items = listOf(
        Screen.Home,
        Screen.Hot,
    )
    BottomNavigation {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                icon = { Icon(screen.icon, contentDescription = null) },
                onClick = {

//                    Log.i("","====================")
//                    currentDestination?.hierarchy?.forEach {
//                        Log.i("",it.navigatorName)
//                    }
//                    Log.i("","====================")

                    navHostController.navigate(screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = {
                    Text(text = stringResource(id = screen.textId))
                }
            )
        }
    }
}