package com.charles.composemiaojian.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.charles.composemiaojian.MiaoViewModel

@Composable
fun ChatPage() {
    val viewModel: MiaoViewModel = viewModel()
    Column (modifier = Modifier
        .layout { measurable, constraints ->
            val placeable = measurable.measure(constraints = constraints)
            val offset = if (viewModel.chatting) 0 else placeable.width
            layout(placeable.width, placeable.height) {
                placeable.placeRelative(offset, 0)
            }
        }
        .fillMaxSize()
        .background(Color.Magenta)
    ) {
        viewModel.currentChat?.friend?.name?.let { MiaoTopBar(title = it) }
    }
}