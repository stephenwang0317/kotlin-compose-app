package com.example.homework

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.homework.component.MyExtendScaffold
import com.example.homework.compositionLocal.LocalNavController
import com.example.homework.compositionLocal.LocalUserViewModel
import com.example.homework.ui.theme.Purple500
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun HotView() {

    val userViewModel = LocalUserViewModel.current
    val navHostController = LocalNavController.current

    val list: List<String> = listOf(
        "https://i2.hdslb.com/bfs/face/d399d6f5cf7943a996ae96999ba3e6ae2a2988de.jpg",
        "https://i1.hdslb.com/bfs/face/8895c87082beba1355ea4bc7f91f2786ef49e354.jpg",
        "https://i0.hdslb.com/bfs/face/566078c52b408571d8ae5e3bcdf57b2283024c27.jpg",
        "https://i2.hdslb.com/bfs/face/31b0d4177a204e95e3489d5c420cea8d53c93fae.jpg"
    )

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
            MyExtendScaffold {
                LazyColumn(
                    contentPadding = it
                ) {
                    items(list) {
                        Image(
                            painter = rememberImagePainter(
                                data = it
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(100.dp, 100.dp)
                        )
                    }
                }
            }


        }
    }
}
