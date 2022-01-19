package com.charles.composemiaojian.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.charles.composemiaojian.MiaoViewModel
import com.charles.composemiaojian.R
import com.charles.composemiaojian.data.Msg
import com.charles.composemiaojian.data.User
import com.charles.composemiaojian.ui.theme.ComposeMiaojianTheme
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun ChatPage() {
    val viewModel: MiaoViewModel = viewModel()
    val offsetPercentX by animateFloatAsState(if (viewModel.chatting) 0f else 1f, TweenSpec(600))
    val chat = viewModel.currentChat ?: return
    Column(
        modifier = Modifier
            .offsetPercent(offsetPercentX = offsetPercentX)
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        var shakingTime by remember {
            mutableStateOf(0)
        }
        MiaoTopBar(title = chat.friend.name) {
            viewModel.endChat()
        }
        Box(
            Modifier
                .background(ComposeMiaojianTheme.colors.chatPage)
                .weight(1f)
        ) {
            Box(
                Modifier
                    .alpha(ComposeMiaojianTheme.colors.chatPageBgAlpha)
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_discovery_outlined),
                    contentDescription = "bg left",
                    Modifier
                        .align(Alignment.CenterStart)
                        .padding(bottom = 100.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_discovery_outlined),
                    contentDescription = "bg top",
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(horizontal = 24.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_discovery_outlined),
                    contentDescription = "bg bottom",
                    Modifier
                        .align(Alignment.BottomEnd)
                        .padding(vertical = 200.dp)
                )
            }
            val shakingOffset = remember {
                Animatable(0f)
            }
            LaunchedEffect(key1 = shakingTime) {
                if (shakingTime != 0) {
                    shakingOffset.animateTo(
                        targetValue = 0f,
                        animationSpec = spring(dampingRatio = 0.3f, stiffness = 600f),
                        initialVelocity = -2000f
                    )
                }
            }
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .offset(shakingOffset.value.dp, shakingOffset.value.dp)
            ) {
                items(chat.msgs.size) { index ->
                    val msg = chat.msgs[index]
                    MessageItem(msg, shakingTime, chat.msgs.size - index - 1)
                }
            }
        }
        ChatBottomBar {
            viewModel.boom(chat)
            shakingTime++
        }
    }
}

@Composable
fun MessageItem(msg: Msg, shakingTime: Int, shakingLevel: Int) {
    val shakingAngleBubble = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = shakingTime) {
        if (shakingTime != 0) {
            delay(shakingLevel.toLong() * 30)
            shakingAngleBubble.animateTo(
                targetValue = 0f,
                animationSpec = spring(dampingRatio = 0.4f, stiffness = 500f),
                initialVelocity = 1200f / (1 + shakingLevel * 0.4f)
            )
        }
    }
    if (msg.from == User.Me) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
            Arrangement.End
        ) {
            val bubbleColor = ComposeMiaojianTheme.colors.bubbleMe
            Text(text = msg.text,
                Modifier
                    .graphicsLayer(
                        rotationZ = shakingAngleBubble.value,
                        transformOrigin = TransformOrigin(1f, 0f)
                    )
                    .drawBehind {
                        val bubble = Path().apply {
                            val rect = RoundRect(
                                left = 10.dp.toPx(),
                                top = 0f,
                                right = size.width - 10.dp.toPx(),
                                bottom = size.height,
                                radiusX = 4.dp.toPx(),
                                radiusY = 4.dp.toPx()
                            )
                            addRoundRect(rect)
                            moveTo(x = size.width - 10.dp.toPx(), y = 15.dp.toPx())
                            lineTo(x = size.width - 5.dp.toPx(), y = 20.dp.toPx())
                            lineTo(x = size.width - 10.dp.toPx(), y = 25.dp.toPx())
                            close()
                        }
                        drawPath(bubble, bubbleColor)
                    }
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                color = ComposeMiaojianTheme.colors.textPrimaryMe
            )
            Image(
                painter = painterResource(id = msg.from.avatar),
                contentDescription = msg.from.name,
                Modifier
                    .graphicsLayer(
                        rotationZ = shakingAngleBubble.value * 0.6f,
                        transformOrigin = TransformOrigin(1f, 0f)
                    )
                    .size(40.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
        }
    } else {
        Row(
            Modifier
                .fillMaxSize()
                .padding(8.dp),
            Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = msg.from.avatar),
                contentDescription = msg.from.name,
                Modifier
                    .graphicsLayer(
                        rotationZ = shakingAngleBubble.value * 0.6f,
                        transformOrigin = TransformOrigin(0f, 0f)
                    )
                    .size(40.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            val bubbleColor = ComposeMiaojianTheme.colors.bubbleOthers
            Text(text = msg.text,
                Modifier
                    .graphicsLayer(
                        rotationZ = shakingAngleBubble.value,
                        transformOrigin = TransformOrigin(0f, 0f)
                    )
                    .drawBehind {
                        val bubble = Path().apply {
                            val rect = RoundRect(
                                left = 10.dp.toPx(),
                                top = 0f,
                                right = size.width - 10.dp.toPx(),
                                bottom = size.height,
                                radiusX = 4.dp.toPx(),
                                radiusY = 4.dp.toPx()
                            )
                            addRoundRect(rect)
                            moveTo(x = 10.dp.toPx(), y = 15.dp.toPx())
                            lineTo(x = 5.dp.toPx(), y = 20.dp.toPx())
                            lineTo(x = 10.dp.toPx(), y = 25.dp.toPx())
                            close()
                        }
                        drawPath(bubble, bubbleColor)
                    }
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                color = ComposeMiaojianTheme.colors.textPrimaryMe
            )
        }
    }
}

@Composable
fun ChatBottomBar(onBombClicked: () -> Unit) {
    var editingText by remember {
        mutableStateOf("")
    }
    Row(
        Modifier
            .fillMaxWidth()
            .background(ComposeMiaojianTheme.colors.bottomBar)
            .padding(horizontal = 4.dp, vertical = 0.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_discovery_outlined),
            contentDescription = "voice button",
            Modifier
                .align(Alignment.CenterVertically)
                .padding(4.dp)
                .size(28.dp),
            tint = ComposeMiaojianTheme.colors.icon
        )
        BasicTextField(
            value = editingText,
            onValueChange = { editingText = it },
            Modifier
                .weight(1f)
                .padding(4.dp, 8.dp)
                .height(40.dp)
                .clip(MaterialTheme.shapes.small)
                .background(ComposeMiaojianTheme.colors.textFieldBackground)
                .padding(start = 8.dp, top = 10.dp, end = 8.dp),
            cursorBrush = SolidColor(ComposeMiaojianTheme.colors.textPrimary)
        )
        Text(
            text = "\uD83D\uDCA3",
            Modifier
                .clickable(onClick = onBombClicked)
                .padding(4.dp)
                .align(Alignment.CenterVertically),
            fontSize = 24.sp
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_discovery_outlined),
            contentDescription = "add button",
            Modifier
                .align(Alignment.CenterVertically)
                .padding(4.dp)
                .size(28.dp),
            tint = ComposeMiaojianTheme.colors.icon
        )
    }
}

fun Modifier.offsetPercent(offsetPercentX: Float = 0f, offsetPercentY: Float = 0f) =
    this.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints = constraints)
        layout(placeable.width, placeable.height) {
            val offsetX = (offsetPercentX * placeable.width).roundToInt()
            val offsetY = (offsetPercentY * placeable.height).roundToInt()
            placeable.placeRelative(offsetX, offsetY)
        }
    }