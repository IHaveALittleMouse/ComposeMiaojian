package com.charles.composemiaojian.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.charles.composemiaojian.MiaoViewModel
import com.charles.composemiaojian.R
import com.charles.composemiaojian.ui.theme.ComposeMiaojianTheme

@Composable
fun MiaoTopBar(title: String, onBack: (() -> Unit)? = null) {
    Box(
        Modifier
            .background(ComposeMiaojianTheme.colors.background)
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .height(48.dp)
        ) {
            if (onBack != null) {
                Icon(
                    painterResource(R.drawable.ic_discovery_outlined),
                    contentDescription = "back",
                    Modifier
                        .clickable {
                            onBack.invoke()
                        }
                        .align(Alignment.CenterVertically)
                        .size(36.dp)
                        .padding(8.dp),
                    tint = ComposeMiaojianTheme.colors.icon
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            val viewModel: MiaoViewModel = viewModel()
            Icon(
                painter = painterResource(id = R.drawable.ic_discovery_outlined),
                contentDescription = "switch theme",
                Modifier
                    .clickable {
                        viewModel.theme = when (viewModel.theme) {
                            ComposeMiaojianTheme.Theme.Light -> ComposeMiaojianTheme.Theme.Dark
                            ComposeMiaojianTheme.Theme.Dark -> ComposeMiaojianTheme.Theme.NewYear
                            ComposeMiaojianTheme.Theme.NewYear -> ComposeMiaojianTheme.Theme.Light
                        }
                    }
                    .align(Alignment.CenterVertically)
                    .size(36.dp)
                    .padding(8.dp),
                tint = ComposeMiaojianTheme.colors.icon
            )
        }
        Text(
            text = title,
            Modifier.align(Alignment.Center),
            color = ComposeMiaojianTheme.colors.textPrimary
        )
    }
}