package com.apps.hw2.net.mapper

import com.apps.hw2.db.entity.messages.MessagesEntity
import com.apps.hw2.db.entity.messages.Reaction
import com.apps.hw2.net.model.MessageObject
import com.apps.hw2.net.model.ReactionObject

class MessagesObjectMapper {
    fun mapMessageObjectToMessageEntity(messageObject: MessageObject): MessagesEntity {
        return MessagesEntity(
            id = messageObject.messageId,
            isMeMessage = messageObject.isMeMessage,
            avatarUrl = messageObject.avatarUrl ?: "",
            senderFullName = messageObject.senderFullName,
            senderId = messageObject.senderId,
            streamId = messageObject.streamId,
            subject = messageObject.subject,
            timestamp = messageObject.timestamp,
            content = messageObject.messageText,
            reactions = mapReactionObjectsToReaction(messageObject.reactions)
        )
    }
    
    fun mapMessageObjectsToMessagesEntities(messageObjects: List<MessageObject>): List<MessagesEntity> {
        return messageObjects.map {
            mapMessageObjectToMessageEntity(it)
        }
    }

    private fun mapReactionObjectsToReaction(reactionObjects: List<ReactionObject>): List<Reaction> {
        return reactionObjects.map {
            Reaction(
                emojiName = it.emojiName,
                emojiCode = it.emojiCode,
                reactionType = it.reactionType,
                userId = it.userId
            )
        }
    }
}