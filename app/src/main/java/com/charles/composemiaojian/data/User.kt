package com.charles.composemiaojian.data

import androidx.annotation.DrawableRes
import com.charles.composemiaojian.R

class User(
    val id: String,
    val name: String,
    @DrawableRes val avatar: Int
) {
    companion object {
        val Me: User = User(id = "Charles", name = "Charles Ni", R.drawable.avatar_charles)
    }
}