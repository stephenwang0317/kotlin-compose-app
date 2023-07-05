package com.example.homework.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.ui.Modifier
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.homework.R
import com.example.homework.model.entity.baiduhot.Content
import com.example.homework.model.entity.baiduhot.TopContent

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun HotCard(
    modifier: Modifier = Modifier,
    content: Content
) {
    var titleLines by remember {
        mutableStateOf(0)
    }
    var contentLines by remember {
        mutableStateOf(0)
    }
    val uri: Uri = Uri.parse(content.rawUrl)
    val context = LocalContext.current

    Card(
        enabled = true,
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(Intent.createChooser(intent, "选择浏览器"))
        },
        modifier = Modifier
            .padding(2.dp),
        elevation = 2.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                painter = rememberImagePainter(
                    data = content.img,
                    builder = {
                        placeholder(R.drawable.ic_launcher_background)
                    }
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp, 150.dp)
                    .padding(top = 5.dp, bottom = 5.dp)
                    .clip(RoundedCornerShape(5)),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = content.word,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(
                        top = 10.dp, bottom = 10.dp
                    ),
                    onTextLayout = {
                        titleLines = it.lineCount
                    }
                )
                for (i in titleLines until 2) {
                    Text(
                        text = "",
                        fontSize = 16.sp,
                    )
                }
                Text(
                    text = content.desc,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = {
                        contentLines = it.lineCount
                    }
                )
                for (i in contentLines until 3) {
                    Text(
                        text = "",
                        fontSize = 14.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 10.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = null,
                        modifier = Modifier.size(13.dp, 13.dp),
                        tint = Color.Red
                    )
                    Text(
                        text = stringResource(id = R.string.hot_rate, content.hotScore),
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Monospace,
                        fontStyle = FontStyle.Italic
                    )

                }
            }
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun TopHotCard(
    modifier: Modifier = Modifier,
    content: TopContent
) {
    val uri: Uri = Uri.parse(content.rawUrl)
    val context = LocalContext.current

    Card(
        enabled = true,
        onClick = {
            val intent: Intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(Intent.createChooser(intent, "选择浏览器"))
        },
        modifier = Modifier
            .padding(2.dp),
        elevation = 2.dp,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box() {
                Image(
                    painter = rememberImagePainter(
                        data = content.img,
                        builder = {
                            placeholder(R.drawable.ic_launcher_background)
                        }
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(top = 5.dp, bottom = 5.dp)
                        .clip(RoundedCornerShape(5)),
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.top),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp, 50.dp)
                        .padding(top = 8.dp),
                    tint = Color.Red
                )
            }
            Text(
                text = content.word,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(
                    top = 10.dp, bottom = 10.dp
                ),
            )
            Text(
                text = content.desc,
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = null,
                    modifier = Modifier.size(13.dp, 13.dp),
                    tint = Color.Red
                )
                Text(
                    text = stringResource(id = R.string.hot_rate, content.hotScore),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Monospace,
                    fontStyle = FontStyle.Italic
                )

            }
        }
    }
}