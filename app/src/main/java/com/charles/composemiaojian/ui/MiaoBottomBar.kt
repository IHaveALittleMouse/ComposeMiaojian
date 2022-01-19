package com.charles.composemiaojian.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.charles.composemiaojian.R
import com.charles.composemiaojian.ui.theme.ComposeMiaojianTheme

@Composable
fun MiaoBottomBar(selectedTab: Int, onSelectedChanged: (Int) -> Unit) {
    Row(Modifier.background(ComposeMiaojianTheme.colors.bottomBar)) {
        TabItem(
            if (selectedTab == 0) R.drawable.ic_chat_filled else R.drawable.ic_chat_outlined,
            "chat",
            if (selectedTab == 0) ComposeMiaojianTheme.colors.iconCurrent else ComposeMiaojianTheme.colors.icon,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChanged(0)
                }
        )
        TabItem(
            if (selectedTab == 1) R.drawable.ic_contacts_filled else R.drawable.ic_contacts_outlined,
            "contacts",
            if (selectedTab == 1) ComposeMiaojianTheme.colors.iconCurrent else ComposeMiaojianTheme.colors.icon,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChanged(1)
                }
        )
        TabItem(
            if (selectedTab == 2) R.drawable.ic_discovery_filled else R.drawable.ic_discovery_outlined,
            "discovery",
            if (selectedTab == 2) ComposeMiaojianTheme.colors.iconCurrent else ComposeMiaojianTheme.colors.icon,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChanged(2)
                }
        )
        TabItem(
            if (selectedTab == 3) R.drawable.ic_user_filled else R.drawable.ic_user_outlined,
            "me",
            if (selectedTab == 3) ComposeMiaojianTheme.colors.iconCurrent else ComposeMiaojianTheme.colors.icon,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChanged(3)
                }
        )
    }
}

@Composable
fun TabItem(
    @DrawableRes iconId: Int,
    title: String,
    tint: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(painterResource(iconId), title, Modifier.size(24.dp), tint = tint)
        Text(title, fontSize = 14.sp, color = tint)
    }
}

// Preview code block ------------------------------------------------------------------------------

@Preview(showBackground = true)
@Composable
fun TabItemPreview() {
    TabItem(
        iconId = R.drawable.ic_chat_outlined,
        title = "chat",
        tint = ComposeMiaojianTheme.colors.icon
    )
}

@Preview(showBackground = true)
@Composable
fun MiaoBottomBarPreviewLight() {
    ComposeMiaojianTheme(ComposeMiaojianTheme.Theme.Light) {
        var selectedTab by remember {
            mutableStateOf(0)
        }
        MiaoBottomBar(selectedTab) { selectedTab = it }
    }
}

@Preview(showBackground = true)
@Composable
fun MiaoBottomBarPreviewDark() {
    ComposeMiaojianTheme(ComposeMiaojianTheme.Theme.Dark) {
        var selectedTab by remember {
            mutableStateOf(0)
        }
        MiaoBottomBar(selectedTab) { selectedTab = it }
    }
}

@Preview(showBackground = true)
@Composable
fun MiaoBottomBarPreviewNewYear() {
    ComposeMiaojianTheme(ComposeMiaojianTheme.Theme.NewYear) {
        var selectedTab by remember {
            mutableStateOf(0)
        }
        MiaoBottomBar(selectedTab) { selectedTab = it }
    }
}
