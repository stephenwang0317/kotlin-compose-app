package com.example.homework


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homework.compositionLocal.LocalNavController
import com.example.homework.model.entity.ArticleModelItem


@ExperimentalMaterialApi
@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,

    titleFontSize: Int = 22,
    contentFontSize: Int = 16,
    otherFontSize: Int = 12,

    articleItem: ArticleModelItem,

    ) {
    val navHostController = LocalNavController.current

    Card(
        enabled = true,
        onClick = {},
        shape = RoundedCornerShape(10),
        modifier = Modifier
            .padding(2.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .background(Color.LightGray)
                .clickable { navHostController.navigate("DetailArticle/" + articleItem.artId) }
        ) {

            Text(
                text = articleItem.artTitle ?: "",
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth(),
                fontSize = titleFontSize.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,

                )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    Icons.Rounded.Favorite,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(otherFontSize.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = stringResource(
                        id = R.string.like_num,
                        (articleItem.artLike ?: 0)
                    ),
                    fontSize = otherFontSize.sp,
                    textAlign = TextAlign.End
                )
            }

            var lines by remember {
                mutableStateOf(0)
            }
            val minLines = 3

            Text(
                text = articleItem.artContent ?: "",
                fontSize = contentFontSize.sp,
                modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                maxLines = minLines,
                onTextLayout = {
                    lines = it.lineCount
                }
            )

            for (i in lines until minLines) {
                Text(
                    text = "",
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                    fontSize = contentFontSize.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(id = R.string.show_more),
                    modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp)
                        .clickable { navHostController.navigate("DetailArticle/" + articleItem.artId) },
                    fontSize = 10.sp,
                    color = Color.Blue,
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline
                )
            }


            Row(
                modifier = Modifier.padding(start = 5.dp, end = 5.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = articleItem.artAuthorName ?: "",
                        fontSize = otherFontSize.sp
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(text = articleItem.artTime ?: "", fontSize = otherFontSize.sp)
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }

}
