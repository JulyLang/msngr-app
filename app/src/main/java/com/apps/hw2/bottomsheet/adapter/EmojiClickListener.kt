package com.apps.hw2.bottomsheet.adapter

import com.apps.hw2.emoji.EmojiItem

fun interface EmojiClickListener {
    fun onClick(emojiItem: EmojiItem)
}