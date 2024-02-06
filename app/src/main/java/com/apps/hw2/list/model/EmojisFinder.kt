package com.apps.hw2.list.model

import com.apps.hw2.model.EmojiCounter

object EmojisFinder {
    fun findEmojiCounterByCode(emojiCode: String, emojis: List<EmojiCounter>): EmojiCounter? {
        return emojis.find { it.code == emojiCode }
    }
}