package com.example.homework

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homework.component.MyFab
import com.example.homework.compositionLocal.LocalNavController
import com.example.homework.compositionLocal.LocalUserViewModel
import com.example.homework.model.entity.UserModel
import com.example.homework.viewmodel.NewArticleItemViewModel
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun NewArticlePage(
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val navHostController = LocalNavController.current
    val userViewModel = LocalUserViewModel.current

    val newArticleItemViewModel = NewArticleItemViewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "新建帖子") },
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
        floatingActionButton = {
            MyFab(
                onFinish = {
                    coroutineScope.launch {
                        newArticleItemViewModel.postArticle(
                            userViewModel.userInfo ?: UserModel()
                        )
                        if (newArticleItemViewModel.newArticleIfo?.artId != null) {
                            navHostController.popBackStack()
                        }
                    }
                },
                icon = {
                    if (!newArticleItemViewModel.loading)
                        Icon(imageVector = Icons.Default.Done, contentDescription = null)
                    else
                        CircularProgressIndicator(modifier = Modifier.size(20.dp))
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedTextField(
                value = newArticleItemViewModel.title,
                onValueChange = { newArticleItemViewModel.title = it},
                singleLine = true,
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 15.dp,
                        bottom = 10.dp
                    )
                    .fillMaxWidth(),
                label = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Title",
                            fontSize = 20.sp
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier.padding(
                            start = 5.dp, end = 5.dp
                        )
                    )
                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Divider(
                modifier = Modifier.padding(
                    top = 5.dp,
                    bottom = 0.dp
                )
            )
            OutlinedTextField(
                value = newArticleItemViewModel.content,
                onValueChange = { newArticleItemViewModel.content = it},
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                shape = RoundedCornerShape(5),
                label = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            modifier = Modifier.padding(
                                start = 5.dp, end = 5.dp
                            )
                        )
                        Text(
                            text = "Content",
                            fontSize = 20.sp
                        )
                    }
                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}