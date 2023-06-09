package com.example.homework

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.homework.component.*
import com.example.homework.compositionLocal.LocalUserViewModel
import com.example.homework.ui.theme.Purple500
import com.example.homework.viewmodel.ArticleViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val userViewModel = LocalUserViewModel.current
    val articleViewModel = ArticleViewModel()

    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

    LaunchedEffect(Unit) {
        articleViewModel.getAllArticle()
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

            MyExtendScaffold {
                if (articleViewModel.loading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(100.dp),
                            color = Purple500
                        )
                    }
                } else {
                    SwipeRefresh(
                        state = rememberSwipeRefreshState(isRefreshing = articleViewModel.isRefreshing),
                        onRefresh = {
                            coroutineScope.launch {
                                articleViewModel.refresh()
                            }
                        }
                    ) {
                        LazyColumn(
                            contentPadding = it,
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            content = {
                                items(
                                    articleViewModel.tmpList
                                ) { article ->
                                    ArticleCard(articleItem = article)
                                }
                            },
                            state = scrollState,
                        )
                    }
                }
            }
        }
    }
}
