package com.example.homework.screen

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.homework.R
import com.example.homework.compositionLocal.LocalUserViewModel
import com.example.homework.model.entity.UserModel
import com.example.homework.model.service.UserService
import com.example.homework.viewmodel.ImageViewModel
import kotlinx.coroutines.launch

@Composable
//@Preview(showBackground = true)
fun ChangeAvatar(
    modifier: Modifier = Modifier,
) {
    val current = LocalUserViewModel.current
    val userModel = current.userInfo
    val user_avatar = stringResource(R.string.img_base_url) + (userModel?.userAvatar)

    var imageUri by remember {
        mutableStateOf<Uri>(Uri.parse(user_avatar))
    }
    val context = LocalContext.current
    var imageVM = ImageViewModel()
    val coroutineScope = rememberCoroutineScope()
    val openSelectPhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            it?.let {
                Log.i("+=============", it.toString())
                imageUri = it
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
//        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "修改头像",
            fontSize = 30.sp,
            modifier = Modifier.padding(30.dp)
        )
        Image(
            painter = rememberImagePainter(
                data = imageUri,
                builder = { placeholder(R.drawable.ic_launcher_background) }
            ),
            contentDescription = null,
            modifier = Modifier
                .clip(
                    shape = CircleShape
                )
                .size(200.dp, 200.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = CircleShape
                )
                .clickable {
                    openSelectPhotoLauncher.launch("image/*")
                }
        )
        Text(
            text = "点击头像选择图片",
            modifier = Modifier.padding(5.dp),
            fontSize = 10.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    val res = imageVM.uploadImage(
                        uri = imageUri,
                        context = context,
                        user_id = userModel!!.userId ?: 100000
                    )
                    if (res.code == 0) {
                        Toast.makeText(context, "成功", Toast.LENGTH_SHORT).show()
                        current.updateInfo()
                    } else {
                        Toast.makeText(context, res.msg, Toast.LENGTH_SHORT).show()
                        imageUri = Uri.parse(user_avatar)
                    }
                }
            },
            enabled = !imageVM.loading
        ) {
            if (!imageVM.loading) {
                Text(
                    text = "提交", fontSize = 20.sp,
                    modifier = Modifier.padding(
                        start = 15.dp,
                        end = 15.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
                )
            } else {
                CircularProgressIndicator()
            }
        }
    }
}