package com.charles.composemiaojian.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.charles.composemiaojian.MiaoViewModel
import com.charles.composemiaojian.data.Chat
import com.charles.composemiaojian.ui.theme.ComposeMiaojianTheme

@Composable
fun ChatList(chats: List<Chat>) {
    Column(
        Modifier
            .fillMaxSize()
            .background(ComposeMiaojianTheme.colors.background)
    ) {
        MiaoTopBar(title = "Compose Miaojian")
        ChatListItemContainer(chats)
    }
}

@Composable
private fun ChatListItemContainer(chats: List<Chat>) {
    LazyColumn(
        Modifier
            .background(ComposeMiaojianTheme.colors.listItem)
    ) {
        itemsIndexed(chats) { index, chat ->
            ChatListItem(chat)
            if (index < chats.lastIndex) {
                Divider(
                    startIndent = 58.dp,
                    color = ComposeMiaojianTheme.colors.divider,
                    thickness = 0.8f.dp
                )
            }
        }
    }
}

@Composable
private fun ChatListItem(chat: Chat) {
    val viewModel: MiaoViewModel = viewModel()
    Row(
        Modifier
            .clickable {
                viewModel.startChat(chat)
            }
            .fillMaxWidth()
    ) {
        Image(
            painterResource(chat.friend.avatar),
            "avatar of ${chat.friend.name}",
            Modifier
                .padding(4.dp)
                .size(48.dp)
                .unread(!chat.msgs.last().read, ComposeMiaojianTheme.colors.badge)
                .clip(RoundedCornerShape(4.dp))
        )
        Column(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                chat.friend.name,
                fontSize = 17.sp,
                color = ComposeMiaojianTheme.colors.textPrimary
            )
            Text(
                chat.msgs.last().text,
                fontSize = 14.sp,
                color = ComposeMiaojianTheme.colors.textSecondary
            )
        }
        Text(
            chat.msgs.last().time,
            Modifier.padding(8.dp, 8.dp, 12.dp, 8.dp),
            color = ComposeMiaojianTheme.colors.textSecondary
        )
    }
}

fun Modifier.unread(show: Boolean, color: Color): Modifier = this.drawWithContent {
    drawContent()
    if (show) {
        drawCircle(
            color,
            5.dp.toPx(),
            Offset(size.width - 1.dp.toPx(), 1.dp.toPx())
        )
    }
}


// Preview code block ------------------------------------------------------------------------------

@Preview
@Composable
fun ChatListPreview() {
}