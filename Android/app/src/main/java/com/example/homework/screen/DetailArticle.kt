package com.example.homework

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homework.component.CommentBox
import com.example.homework.compositionLocal.LocalNavController
import com.example.homework.compositionLocal.LocalUserViewModel
import com.example.homework.model.entity.CommentItem
import com.example.homework.model.entity.UserModel
import com.example.homework.ui.theme.Purple500
import com.example.homework.viewmodel.ArticleItemViewModel
import com.example.homework.viewmodel.CommentViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun DetailArticle(
    modifier: Modifier = Modifier,
    art_id: Int? = 0
) {
    val coroutineScope = rememberCoroutineScope()
    val navHostController = LocalNavController.current
    val scollState = rememberLazyListState()

    val userViewModel = LocalUserViewModel.current
    val artViewModel = ArticleItemViewModel()
    val comViewModel = CommentViewModel()


    LaunchedEffect(Unit) {
        async { artViewModel.getArtById(art_id = art_id ?: 0) }
        async {
            artViewModel.checkIfLike(
                art_id = art_id ?: 0,
                user_id = userViewModel.userInfo?.userId ?: 0
            )
        }
        async {
            comViewModel.getCommentOfArticle(art_id = art_id ?: 0)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "帖子详情") },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch { navHostController.popBackStack() }
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            contentPadding = padding,
            state = scollState,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                item {
                    DetailContent(
                        viewModel = artViewModel,
                        art_id = art_id,
                        usrModel = userViewModel.userInfo ?: UserModel(),
                        cmtViewModel = comViewModel
                    )
                }

                if (comViewModel.loading) {
                    item { CircularProgressIndicator() }
                } else {
                    itemsIndexed(
                        comViewModel.comList,
                    ) { index, item ->
                        CommentBox(
                            comment = item,
                            modifier = Modifier.fillMaxWidth(),
                            index = index
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            top = 10.dp,
                            bottom = 10.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Divider(thickness = 5.dp, modifier = Modifier.weight(1f))
                        Text(text = "The End", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Divider(thickness = 5.dp, modifier = Modifier.weight(1f))
                    }
                }
            }
        )
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    viewModel: ArticleItemViewModel,
    art_id: Int?,
    usrModel: UserModel,
    cmtViewModel: CommentViewModel
) {

    val coroutineScope = rememberCoroutineScope()
    val naviCon = LocalNavController.current

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = viewModel.artItem.artTitle ?: "",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                start = 5.dp, end = 5.dp,
                top = 10.dp, bottom = 10.dp
            )
        )

        Row(
            modifier = Modifier
                .padding(end = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(Icons.Default.Person, contentDescription = null)
            Text(text = viewModel.artItem.artAuthorName ?: "")
            Spacer(modifier = Modifier.width(10.dp))
            Icon(Icons.Default.Schedule, null)
            Text(text = viewModel.artItem.artTime ?: "1900-00-00 00:00:00")
        }

        Row(modifier = Modifier.padding(start = 5.dp, bottom = 10.dp)) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.size(20.dp, 20.dp),
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = (viewModel.artItem.artLike ?: 0).toString())
        }

        Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp)

        Text(
            text = viewModel.artItem.artContent ?: "",
            modifier = Modifier.padding(
                start = 5.dp, end = 5.dp,
                top = 10.dp, bottom = 10.dp
            ),
            fontSize = 22.sp
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (viewModel.isLike == 0)
                CircularProgressIndicator()
            else {
                Icon(
                    imageVector =
                    if (viewModel.isLike == -1)
                        ImageVector.vectorResource(id = R.drawable.heart_plus)
                    else
                        ImageVector.vectorResource(id = R.drawable.heart_check),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier
                        .size(50.dp, 50.dp)
                        .clickable {
                            when (viewModel.isLike) {
                                -1 -> {
                                    coroutineScope.launch {
                                        viewModel.like(
                                            user_id = usrModel.userId ?: 0,
                                            art_id = art_id ?: 0
                                        )
                                    }
                                }

                                1 -> {
                                    coroutineScope.launch {
                                        viewModel.dislike(
                                            user_id = usrModel.userId ?: 0,
                                            art_id = art_id ?: 0
                                        )
                                    }
                                }

                                else -> {}
                            }
                        }
                )
            }
            if (usrModel.userId == viewModel.artItem.artAuthor) {
                if (viewModel.deleting) {
                    CircularProgressIndicator()
                } else {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp, 50.dp)
                            .clickable {
                                coroutineScope.launch {
                                    viewModel.deleteArticle(art_id ?: 0)
                                    naviCon.popBackStack()
                                }
                            })
                }
            }

        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp), thickness = 2.dp
        )

        OutlinedTextField(
            value = cmtViewModel.content,
            onValueChange = { cmtViewModel.content = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(
                    start = 10.dp, end = 10.dp
                ),
            textStyle = TextStyle(
                fontSize = 22.sp
            ),
            placeholder = {
                Text(text = "添加评论")
            },
            label = {
                Row {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    Text(text = "评论", fontSize = 20.sp)
                }
            },
            trailingIcon = {
                if (!cmtViewModel.postLoading) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            coroutineScope.launch {
                                cmtViewModel.postComment(
                                    art_id = art_id ?: 0,
                                    userModel = usrModel
                                )
                            }
                        },
                        tint = Color.Blue
                    )
                } else {
                    CircularProgressIndicator()
                }
            }
        )
    }

}
