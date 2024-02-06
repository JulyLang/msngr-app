package com.apps.hw2.db.mapper

import com.apps.hw2.db.entity.messages.MessagesEntity
import com.apps.hw2.list.model.BaseMessage
import com.apps.hw2.list.model.MyUserMessage
import com.apps.hw2.list.model.UserMessage

class MessagesEntitiesMapper(
    private val myUserId: Int//todo DI
) {
    fun mapMessagesEntitiesToMessagesObjects(messagesEntities: List<MessagesEntity>): List<BaseMessage> {
        return messagesEntities.map {
            if (it.isMyMessage(userId = myUserId)) MyUserMessage(
                id = it.id,
                userAvatar = it.avatarUrl,
                contactMessage = it.content,
                contactName = it.senderFullName,
                emojis = ReactionsMapper(myUserId).mapReactionsToEmojiCounters(it.reactions),
                dateCreated = it.timestamp
            ) else UserMessage(
                id = it.id,
                userAvatar = it.avatarUrl,
                contactName = it.senderFullName,
                contactMessage = it.content,
                emojis = ReactionsMapper(myUserId).mapReactionsToEmojiCounters(it.reactions),
                dateCreated = it.timestamp
            )
        }
    }
}