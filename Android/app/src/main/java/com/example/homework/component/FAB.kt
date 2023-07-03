package com.example.homework.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homework.compositionLocal.LocalNavController
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

@Preview(showBackground = true)
@Composable
fun MyFab(
    modifier: Modifier = Modifier,
    onFinish: ()->Unit = {},
    baseSize: Int = 70,
    animateSize: Int = 20,
    icon: @Composable ()->Unit = {}
) {
//    val scaffoldState = rememberScaffoldState()

    val startVal = -1f * animateSize
    val endVal = 1f * animateSize

    val animatedProgress = remember { Animatable(startVal) }
    val coroutineScope = rememberCoroutineScope()
    val progress = animatedProgress.value

    val changeShape: () -> Unit = {
        val target = animatedProgress.targetValue
        val nextTarget = if (target == endVal) startVal else endVal
        coroutineScope.launch {
            animatedProgress.animateTo(
                targetValue = nextTarget,
                animationSpec = TweenSpec(durationMillis = 600)
            )
        }
        onFinish()
    }

    FloatingActionButton(
        onClick = changeShape,
        shape = CircleShape,
        contentColor = Color.White,
        backgroundColor = Color.DarkGray,
        modifier = Modifier.size((baseSize - abs(progress)).dp)
    ) {
        icon()
    }
}