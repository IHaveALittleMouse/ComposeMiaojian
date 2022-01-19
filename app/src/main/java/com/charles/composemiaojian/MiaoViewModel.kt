package com.charles.composemiaojian

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.charles.composemiaojian.data.Chat
import com.charles.composemiaojian.data.Msg
import com.charles.composemiaojian.data.User
import com.charles.composemiaojian.ui.theme.ComposeMiaojianTheme

class MiaoViewModel : ViewModel() {
    var chats by mutableStateOf(
        listOf(
            Chat(
                friend = User(
                    id = "Gloria",
                    name = "Gloria Zhang",
                    R.drawable.avatar_gloria
                ),
                mutableStateListOf(
                    Msg(
                        User(
                            id = "Gloria",
                            name = "Gloria Zhang",
                            R.drawable.avatar_gloria
                        ),
                        text = "倪竹逸！",
                        time = "15:00"
                    ).apply { read = false }
                )
            ),
            Chat(
                friend = User(
                    id = "Charles",
                    name = "Charles Ni",
                    R.drawable.avatar_charles
                ),
                mutableStateListOf(
                    Msg(
                        User.Me,
                        text = "Working on ComposeMiaojian",
                        time = "15:30"
                    )
                )
            )
        )
    )
    var selectedTab by mutableStateOf(0)
    var theme by mutableStateOf(ComposeMiaojianTheme.Theme.Light)
    var currentChat: Chat? by mutableStateOf(null)
    var chatting by mutableStateOf(false)

    fun startChat(chat: Chat) {
        chatting = true
        currentChat = chat
    }

    fun endChat(): Boolean {
        return when (chatting) {
            true -> {
                chatting = false
                true
            }
            false -> false
        }
    }
}