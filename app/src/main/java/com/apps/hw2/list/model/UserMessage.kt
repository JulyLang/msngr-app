package com.apps.hw2.list.model

import com.apps.hw2.model.EmojiCounter

data class UserMessage(
    val id: Int,
    val userAvatar: String,
    val contactName: String,
    val contactMessage: String,
    val emojis: List<EmojiCounter> = emptyList(),
    override val dateCreated: Long
) : BaseMessage(id, MessageType.USER, dateCreated), IEmojisMessage {
    override fun findEmojiCounterByCode(emojiCode: String): EmojiCounter? {
        return EmojisFinder.findEmojiCounterByCode(emojiCode, emojis)
    }
}