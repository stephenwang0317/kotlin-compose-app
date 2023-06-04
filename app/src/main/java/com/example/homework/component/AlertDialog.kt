package com.example.homework.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun alertDialog(
    isShow: Boolean,
    title: String,
    infoText: String,
    onClose: () -> Unit,
) {
    if (isShow) {
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            title = { Text(text = title) },
            text = { Text(text = infoText) },
            confirmButton = {
                TextButton(onClick = onClose) {
                    Text(text = "确认")
                }
            },
            shape = RoundedCornerShape(10)
        )
    }
}
