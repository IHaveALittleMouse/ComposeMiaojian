package com.charles.composemiaojian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.charles.composemiaojian.ui.theme.ComposeMiaojianTheme
import com.charles.composemiaojian.ui.theme.black
import com.charles.composemiaojian.ui.theme.green3
import com.charles.composemiaojian.ui.theme.white1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMiaojianTheme {
                Column {
                    MiaoBottomBar(0)
                }
            }
        }
    }
}

@Composable
private fun MiaoBottomBar(selectedId: Int) {
    Row(Modifier.background(ComposeMiaojianTheme.colors.bottomBar)) {
        TabItem(
            if (selectedId == 0) R.drawable.ic_chat_filled else R.drawable.ic_chat_outlined,
            "chat",
            if (selectedId == 0) ComposeMiaojianTheme.colors.iconCurrent else ComposeMiaojianTheme.colors.icon,
            Modifier.weight(1f)
        )
        TabItem(
            if (selectedId == 1) R.drawable.ic_contacts_filled else R.drawable.ic_contacts_outlined,
            "contacts",
            if (selectedId == 1) ComposeMiaojianTheme.colors.iconCurrent else ComposeMiaojianTheme.colors.icon,
            Modifier.weight(1f)
        )
        TabItem(
            if (selectedId == 2) R.drawable.ic_discovery_filled else R.drawable.ic_discovery_outlined,
            "discovery",
            if (selectedId == 2) ComposeMiaojianTheme.colors.iconCurrent else ComposeMiaojianTheme.colors.icon,
            Modifier.weight(1f)
        )
        TabItem(
            if (selectedId == 3) R.drawable.ic_user_filled else R.drawable.ic_user_outlined,
            "me",
            if (selectedId == 3) ComposeMiaojianTheme.colors.iconCurrent else ComposeMiaojianTheme.colors.icon,
            Modifier.weight(1f)
        )
    }
}

@Composable
private fun TabItem(
    @DrawableRes iconId: Int,
    title: String,
    tint: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(painterResource(iconId), title, Modifier.size(48.dp), tint = tint)
        Text(title, fontSize = 15.sp, color = tint)
    }
}

// Preview code block ------------------------------------------------------------------------------

@Preview(showBackground = true)
@Composable
fun TabItemPreview() {
    TabItem(iconId = R.drawable.ic_chat_outlined, title = "chat", tint = ComposeMiaojianTheme.colors.icon)
}

@Preview(showBackground = true)
@Composable
fun MiaoBottomBarPreview() {
    MiaoBottomBar(0)
}