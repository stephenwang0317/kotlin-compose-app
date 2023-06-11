package com.example.homework.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun MyLazyColumn(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    headCompose: @Composable ()->Unit = {},
    tailCompose: @Composable ()->Unit = {},
    bodyCompose: @Composable ()->Unit
){
    val listData = (0..9).toList()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = contentPadding,
        content = {
            item {
                headCompose()
            }

            item {
                tailCompose()
            }
        }
    )
}