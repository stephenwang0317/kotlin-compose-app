package com.example.homework

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homework.component.*
import com.example.homework.compositionLocal.LocalNavController
import com.example.homework.compositionLocal.LocalUserViewModel
import com.example.homework.ui.theme.Purple500
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview(showBackground = true)
@Composable
fun HotView() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val userViewModel = LocalUserViewModel.current
    val navHostController = LocalNavController.current

    ProvideWindowInsets {
        rememberSystemUiController().setStatusBarColor(
            Purple500, darkIcons = MaterialTheme.colors.isLight
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsHeight()
            )
            Scaffold(
                topBar = {
                    MyTopAppBar(
                        titleText = "首页",
                        icon = Icons.Default.Menu,
                        drawerState = drawerState
                    )
                },
                bottomBar = {
                    MyBottomBar(navHostController = navHostController)
                },
                drawerContent = {
                    DrawerContent(userViewModel = userViewModel)
                },
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
            ) {
                LazyColumn(content = {
                    val list = (0..100).toList().map {
                        it.toString()
                    }
                    items(list) {
                        list.forEach() {
                            Text(text = it)
                        }
                    }
                })
            }


        }
    }
}