package com.example.homework.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                coroutineScope.launch {
                    userViewModel.logout()
                    navHostController.navigate("LoginPage")
                }
            },
            enabled = !userViewModel.loading,
            modifier = Modifier.padding(20.dp)
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