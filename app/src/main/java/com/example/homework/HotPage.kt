package com.example.homework

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.homework.component.HotCard
import com.example.homework.component.MyExtendScaffold
import com.example.homework.compositionLocal.LocalNavController
import com.example.homework.compositionLocal.LocalUserViewModel
import com.example.homework.ui.theme.Purple500
import com.example.homework.viewmodel.HotViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun HotView() {

    val userViewModel = LocalUserViewModel.current
    val navHostController = LocalNavController.current
    val coroutineScope = rememberCoroutineScope()
    val hotViewModel = HotViewModel()
    val scrollState = rememberLazyListState()

    LaunchedEffect(Unit) {
        hotViewModel.getData()
    }

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
            MyExtendScaffold(title = "热搜") {
                if (hotViewModel.loading) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    hotContent(
                        modifier = Modifier.fillMaxWidth(),
                        hotViewModel = hotViewModel,
                        paddingValues = it,
                        scrollState = scrollState
                    )
                }
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun hotContent(
    modifier: Modifier = Modifier,
    hotViewModel: HotViewModel,
    paddingValues: PaddingValues,
    scrollState: LazyListState
) {
    LazyColumn(
        contentPadding = paddingValues,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        state = scrollState,
        content = {
            items(
                hotViewModel.contents
            ){
                HotCard(
                    content = it
                )
            }
        }
    )
}
