package com.apps.hw2.emoji

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmojiItem(
    val name: String,
    val code: String,
) : Parcelable