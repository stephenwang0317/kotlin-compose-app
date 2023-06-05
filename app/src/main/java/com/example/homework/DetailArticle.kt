package com.example.homework

import android.telecom.Call
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homework.component.MyTopAppBar
import com.example.homework.compositionLocal.LocalNavController
import com.example.homework.ui.theme.Purple500
import com.example.homework.viewmodel.ArticleItemViewModel
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun DetailArticle(
    modifier: Modifier = Modifier,
    art_id: Int? = 0
) {
    val coroutineScope = rememberCoroutineScope()
    val navHostController = LocalNavController.current

    val viewModel = ArticleItemViewModel()
    LaunchedEffect(Unit) {
        viewModel.getArtById(art_id = art_id ?: 0)
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
    ) {

        Column {
            DetailContent(viewModel = viewModel)

        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    viewModel: ArticleItemViewModel
) {
    var isLike by remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()

    Log.i("***********", viewModel.artItem.toString())
    if (viewModel.loading) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(100.dp),
                color = Purple500
            )
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
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
                Text(text = viewModel.artItem.artTime ?: "0000-00-00 00:00:00")
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
                Icon(
                    imageVector =
                    if (!isLike)
                        ImageVector.vectorResource(id = R.drawable.heart_plus)
                    else
                        ImageVector.vectorResource(id = R.drawable.heart_check),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier
                        .size(50.dp, 50.dp)
                        .clickable {
                            if (!isLike) {
                                Log.i(">>>>>>>>>>", viewModel.artItem!!.artLike.toString())
                                coroutineScope.launch { viewModel.like(user_id = 0) }
                                Log.i(">>>>>>>>>>", viewModel.artItem!!.artLike.toString())
                            } else {
                                viewModel.artItem!!.artLike?.minus(1)
                            }
                            isLike = !isLike
                        }
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp), thickness = 2.dp
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(
                        start = 10.dp, end = 10.dp
                    ),
                textStyle = TextStyle(
                    fontSize = 22.sp
                )
            )
        }
    }
}