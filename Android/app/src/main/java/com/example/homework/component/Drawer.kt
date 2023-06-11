package com.example.homework.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homework.LoginView
import com.example.homework.R
import com.example.homework.compositionLocal.LocalNavController
import com.example.homework.compositionLocal.LocalUserViewModel
import com.example.homework.viewmodel.UserViewModel
import kotlinx.coroutines.launch


@Composable
fun DrawerContent(
    modifier: Modifier = Modifier,
) {
    val userViewModel = LocalUserViewModel.current
    val coroutineScope = rememberCoroutineScope()
    val navHostController = LocalNavController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.08f))
        DrawerHead(userViewModel = userViewModel, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.fillMaxHeight(0.02f))
        Divider(thickness = 2.dp, modifier = Modifier.fillMaxWidth(0.8f))
        Spacer(modifier = Modifier.fillMaxHeight(0.05f))

        MyText(text = "我的帖子", onclick = { navHostController.navigate("MyArticle") })
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(top = 5.dp, bottom = 5.dp),
                startIndent = 20.dp
            )
        }
        MyText(text = "我的评论", onclick = { navHostController.navigate("MyComment") })
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(top = 5.dp, bottom = 5.dp),
                startIndent = 20.dp
            )
        }
        MyText(text = "我的点赞", onclick = { navHostController.navigate("MyLike") })

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                coroutineScope.launch {
                    navHostController.navigate("ChangeInfo")
                }
            },
            enabled = !userViewModel.loading,
            modifier = Modifier.padding(
                start = 20.dp, end = 20.dp
            ),
            shape = RoundedCornerShape(5)
        ) {
            Text(
                text = stringResource(id = R.string.change_info),
            )
            if (userViewModel.loading) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp))
            }
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    userViewModel.logout()
                    navHostController.navigate("LoginPage")
                }
            },
            enabled = !userViewModel.loading,
            modifier = Modifier.padding(20.dp).width(90.dp),
            shape = RoundedCornerShape(5)
        ) {
            Text(
                text = stringResource(id = R.string.logout),
            )
            if (userViewModel.loading) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
fun DrawerHead(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel,
) {
    val userModel = userViewModel.userInfo
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.fillMaxWidth(0.05f))
        Image(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier
                .clip(
                    shape = CircleShape
                )
                .size(70.dp, 70.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = CircleShape
                )

        )
        Spacer(modifier = Modifier.fillMaxWidth(0.05f))

        Column {
            Text(
                text = userModel?.userName ?: "",
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = userModel?.userId.toString(),
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                color = Color.LightGray,
                fontFamily = FontFamily.Monospace
            )
        }
        Spacer(modifier = Modifier.weight(1.0f))
    }
}

@Preview(showBackground = true)
@Composable
fun MyText(
    modifier: Modifier = Modifier,
    text: String = "我的点赞",
    onclick: () -> Unit = {}
) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                top = 10.dp,
                bottom = 10.dp
            )
            .clickable { onclick() },
        fontSize = 22.sp,
        color = Color.Gray,
        fontFamily = FontFamily.Cursive,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun ChangeInfo(

) {
    val navHostController = LocalNavController.current
    LoginView(
        navHostController = navHostController,
        titleId = R.string.change_info,
        bottomTextId = R.string.back,
        bottomTextClickFunc = { navHostController.popBackStack() },
        isRegister = true
    )
}