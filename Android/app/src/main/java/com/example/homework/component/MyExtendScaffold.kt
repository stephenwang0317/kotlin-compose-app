package com.example.homework.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homework.compositionLocal.LocalNavController
import kotlinx.coroutines.launch

@Composable
fun MyExtendScaffold(
    title: String = "首页",
    content: @Composable (PaddingValues)->Unit,
){
    val navHostController = LocalNavController.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MyTopAppBar(
                titleText = title,
                icon = Icons.Default.Menu,
            ){
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        },
        bottomBar = {
            MyBottomBar(navHostController = navHostController)
        },
        drawerContent = { DrawerContent() },
        drawerShape = RoundedCornerShape(
            topStartPercent = 0,
            topEndPercent = 10,
            bottomStartPercent = 0,
            bottomEndPercent = 10
        ),
        floatingActionButton = {
            MyFab(
                onFinish = { navHostController.navigate("NewArticlePage") },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(50.dp),
                    )
                },
                baseSize = 100
            )
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        content = content
    )
}