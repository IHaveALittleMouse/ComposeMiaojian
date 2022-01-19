package com.charles.composemiaojian.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

private val LightColorPalette = ComposeMiaojianColors(
    bottomBar = white1,
    background = white2,
    listItem = white,
    divider = white3,
    chatPage = white2,
    textPrimary = black3,
    textPrimaryMe = black3,
    textSecondary = grey1,
    onBackground = grey3,
    icon = black,
    iconCurrent = green3,
    badge = red1,
    onBadge = white,
    bubbleMe = green1,
    bubbleOthers = white,
    textFieldBackground = white,
    more = grey4,
    chatPageBgAlpha = 0f,
)

private val DarkColorPalette = ComposeMiaojianColors(
    bottomBar = black1,
    background = black2,
    listItem = black3,
    divider = black4,
    chatPage = black2,
    textPrimary = white4,
    textPrimaryMe = black6,
    textSecondary = grey1,
    onBackground = grey1,
    icon = white5,
    iconCurrent = green3,
    badge = red1,
    onBadge = white,
    bubbleMe = green2,
    bubbleOthers = black5,
    textFieldBackground = black7,
    more = grey5,
    chatPageBgAlpha = 0f,
)

private val NewYearColorPalette = ComposeMiaojianColors(
    bottomBar = red4,
    background = red5,
    listItem = red2,
    divider = red3,
    chatPage = red5,
    textPrimary = white,
    textPrimaryMe = black6,
    textSecondary = grey2,
    onBackground = grey2,
    icon = white5,
    iconCurrent = green3,
    badge = yellow1,
    onBadge = black3,
    bubbleMe = green2,
    bubbleOthers = red6,
    textFieldBackground = red7,
    more = red8,
    chatPageBgAlpha = 1f,
)

private val  LocalComposeMiaojianColors = compositionLocalOf {
    LightColorPalette
}

object ComposeMiaojianTheme {
    val colors: ComposeMiaojianColors
        @Composable
        get() = LocalComposeMiaojianColors.current
    enum class Theme {
        Light, Dark, NewYear
    }
}

@Stable
class ComposeMiaojianColors(
    bottomBar: Color,
    background: Color,
    listItem: Color,
    divider: Color,
    chatPage : Color,
    textPrimary : Color,
    textPrimaryMe : Color,
    textSecondary : Color,
    onBackground : Color,
    icon : Color,
    iconCurrent : Color,
    badge : Color,
    onBadge : Color,
    bubbleMe : Color,
    bubbleOthers : Color,
    textFieldBackground : Color,
    more : Color,
    chatPageBgAlpha : Float,
) {
    var bottomBar: Color by mutableStateOf(bottomBar)
        private set
    var background: Color by mutableStateOf(background)
        private set
    var listItem: Color by mutableStateOf(listItem)
        private set
    var divider: Color by mutableStateOf(divider)
        private set
    var chatPage: Color by mutableStateOf(chatPage)
        private set
    var textPrimary: Color by mutableStateOf(textPrimary)
        private set
    var textPrimaryMe: Color by mutableStateOf(textPrimaryMe)
        private set
    var textSecondary: Color by mutableStateOf(textSecondary)
        private set
    var onBackground: Color by mutableStateOf(onBackground)
        private set
    var icon: Color by mutableStateOf(icon)
        private set
    var iconCurrent: Color by mutableStateOf(iconCurrent)
        private set
    var badge: Color by mutableStateOf(badge)
        private set
    var onBadge: Color by mutableStateOf(onBadge)
        private set
    var bubbleMe: Color by mutableStateOf(bubbleMe)
        private set
    var bubbleOthers: Color by mutableStateOf(bubbleOthers)
        private set
    var textFieldBackground: Color by mutableStateOf(textFieldBackground)
        private set
    var more: Color by mutableStateOf(more)
        private set
    var chatPageBgAlpha: Float by mutableStateOf(chatPageBgAlpha)
        private set
}

@Composable
fun ComposeMiaojianTheme(
    theme: ComposeMiaojianTheme.Theme = ComposeMiaojianTheme.Theme.Dark,
    content: @Composable () -> Unit
) {
    val targetColors = when(theme) {
        ComposeMiaojianTheme.Theme.Light -> LightColorPalette
        ComposeMiaojianTheme.Theme.Dark-> DarkColorPalette
        ComposeMiaojianTheme.Theme.NewYear -> NewYearColorPalette
    }

    val bottomBar = animateColorAsState(targetColors.bottomBar, TweenSpec(2000))
    val background = animateColorAsState(targetColors.background, TweenSpec(2000))
    val listItem = animateColorAsState(targetColors.listItem, TweenSpec(2000))
    val divider = animateColorAsState(targetColors.divider, TweenSpec(2000))
    val chatPage  = animateColorAsState(targetColors.chatPage , TweenSpec(2000))
    val textPrimary  = animateColorAsState(targetColors.textPrimary , TweenSpec(2000))
    val textPrimaryMe  = animateColorAsState(targetColors.textPrimaryMe , TweenSpec(2000))
    val textSecondary  = animateColorAsState(targetColors.textSecondary , TweenSpec(2000))
    val onBackground  = animateColorAsState(targetColors.onBackground , TweenSpec(2000))
    val icon  = animateColorAsState(targetColors.icon , TweenSpec(2000))
    val iconCurrent  = animateColorAsState(targetColors.iconCurrent , TweenSpec(2000))
    val badge  = animateColorAsState(targetColors.badge , TweenSpec(2000))
    val onBadge  = animateColorAsState(targetColors.onBadge , TweenSpec(2000))
    val bubbleMe  = animateColorAsState(targetColors.bubbleMe , TweenSpec(2000))
    val bubbleOthers  = animateColorAsState(targetColors.bubbleOthers , TweenSpec(2000))
    val textFieldBackground  = animateColorAsState(targetColors.textFieldBackground , TweenSpec(2000))
    val more  = animateColorAsState(targetColors.more , TweenSpec(2000))
    val chatPageBgAlpha  = animateFloatAsState(targetColors.chatPageBgAlpha , TweenSpec(2000))

    val colors = ComposeMiaojianColors(
        bottomBar = bottomBar.value,
        background = background.value,
        listItem = listItem.value,
        divider = divider.value,
        chatPage  = chatPage .value,
        textPrimary  = textPrimary .value,
        textPrimaryMe  = textPrimaryMe .value,
        textSecondary  = textSecondary .value,
        onBackground  = onBackground .value,
        icon  = icon .value,
        iconCurrent  = iconCurrent .value,
        badge  = badge .value,
        onBadge  = onBadge .value,
        bubbleMe  = bubbleMe .value,
        bubbleOthers  = bubbleOthers .value,
        textFieldBackground  = textFieldBackground .value,
        more  = more .value,
        chatPageBgAlpha = chatPageBgAlpha.value,
    )
    
    CompositionLocalProvider(LocalComposeMiaojianColors provides colors) {
        MaterialTheme(
            shapes = shapes,
            content = content
        )
    }
}