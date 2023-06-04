package com.example.homework.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.homework.ArticleCard
import com.example.homework.compositionLocal.LocalNavController
import com.example.homework.ui.theme.Purple500
import com.example.homework.viewmodel.UserViewModel

@Composable
fun MyExtendScaffold(
    content: @Composable (PaddingValues)->Unit
){
    val navHostController = LocalNavController.current
    Scaffold(
        topBar = {
            MyTopAppBar(
                titleText = "首页",
                icon = Icons.Default.Menu,
            )
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