package com.example.homework

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.homework.component.CommentBox
import com.example.homework.compositionLocal.LocalNavController
import com.example.homework.compositionLocal.LocalUserViewModel
import com.example.homework.model.entity.ArticleModelItem
import com.example.homework.ui.theme.Purple500
import com.example.homework.viewmodel.ArticleViewModel
import com.example.homework.viewmodel.CommentViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ListOfArticles(
    modifier: Modifier = Modifier,
    title: String = "我的帖子",
) {
    val coroutineScope = rememberCoroutineScope()
    val navHostController = LocalNavController.current
    val userViewModel = LocalUserViewModel.current
    val articleViewModel = ArticleViewModel()
    val scrollState = rememberLazyListState()

    LaunchedEffect(Unit) {
        if (title == "我的帖子") articleViewModel.getUserArticle(userViewModel.userInfo?.userId ?: 0)
        else if (title == "我的点赞") articleViewModel.getUserLike(userViewModel.userInfo?.userId ?: 0)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch { navHostController.popBackStack() }
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) {
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
                state = scrollState
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ListOfComments(
    modifier: Modifier = Modifier,
    title: String = "我的回复",
) {
    val coroutineScope = rememberCoroutineScope()
    val navHostController = LocalNavController.current
    val userViewModel = LocalUserViewModel.current
    val cmtViewModel = CommentViewModel()
    val scrollState = rememberLazyListState()

    LaunchedEffect(Unit) {
        cmtViewModel.getCommentOfUser(user_id = userViewModel.userInfo?.userId ?: 0)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch { navHostController.popBackStack() }
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        if (cmtViewModel.loading) {
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
            LazyColumn(
                contentPadding = it,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                content = {
                    items(
                        cmtViewModel.comList
                    ) { cmt ->
                        CommentBox(comment = cmt, showFloor = false, index = 0) {
                            navHostController.navigate("DetailArticle/" + cmt.cmtArtId)
                        }
                    }
                },
                state = scrollState
            )
        }
    }
}