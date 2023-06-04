package com.example.homework.component

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MyTopAppBar(
    titleText: String,
    icon: ImageVector,
    onclick: ()->Unit
) {

    TopAppBar(
        title = {  // 可设置标题
            Text(
                text = titleText,
            )
        },
        navigationIcon = {
            IconButton(onClick = onclick) {
                Icon(icon, contentDescription = null)
            }
        },
    )
}