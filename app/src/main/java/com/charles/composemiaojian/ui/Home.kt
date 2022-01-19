package com.charles.composemiaojian.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.charles.composemiaojian.MiaoViewModel
import com.charles.composemiaojian.ui.theme.ComposeMiaojianTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Home(viewModel: MiaoViewModel) {
    ComposeMiaojianTheme(viewModel.theme) {
        Column {
            HorizontalPager(
                count = 4,
                Modifier.weight(1f)
            ) { page ->
                when (page) {
                    0 -> ChatList(viewModel.chats)
                    1 -> Box(Modifier.fillMaxSize())
                    2 -> Box(Modifier.fillMaxSize())
                    3 -> Box(Modifier.fillMaxSize())
                }
            }
            MiaoBottomBar(viewModel.selectedTab) {
                viewModel.selectedTab = it
            }
        }
    }
}
