package com.apps.hw2.list.model

import com.apps.hw2.model.EmojiCounter

interface IEmojisMessage {
    fun findEmojiCounterByCode(emojiCode: String): EmojiCounter?
}