package com.example.homework.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homework.R
import com.example.homework.model.entity.CommentItem

@Composable
fun CommentBox(
    modifier: Modifier = Modifier,
    comment: CommentItem,
    index: Int,
    showFloor: Boolean = true,
    onclick: () -> Unit = {}
) {
    Log.i("=========comment", comment.toString())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp)
            .clickable { onclick() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 10.dp)
        ) {
            Text(
                text = comment.cmtAuthorName,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = comment.cmtTime ?: "1900-00-00 00:00:00", color = Color.Gray)
            if (showFloor) {
                Text(
                    text = stringResource(R.string.floor_index, index + 1),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Text(
            text = comment.cmtContent,
            fontStyle = FontStyle.Italic,
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 3.dp), thickness = 1.dp, color = Color.DarkGray
        )
    }
}