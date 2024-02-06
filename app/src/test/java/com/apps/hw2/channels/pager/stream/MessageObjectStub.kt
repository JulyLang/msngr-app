package com.apps.hw2.channels.pager.stream

import com.apps.hw2.net.model.MessageObject
import com.apps.hw2.net.model.ReactionObject

object MessageObjectStub {
    val MY_NET_MESSAGE_WITHOUT_EMOJIS = MessageObject(
        messageId = 65457,
        isMeMessage = true,
        avatarUrl = "",
        reactions = emptyList(),
        senderFullName = "Av An Am",
        senderId = 98786,
        streamId = 98827866,
        subject = "MyM",
        timestamp = 7877878L,
        messageText = "Hi"
    )

    val MY_NET_MESSAGE_WITH_EMOJIS = MessageObject(
        messageId = 65457,
        isMeMessage = true,
        avatarUrl = "",
        reactions = listOf(
            ReactionObject(
                emojiCode = "1",
                emojiName = "2",
                reactionType = "3",
                userId = 111
            )
        ),
        senderFullName = "Av An Am",
        senderId = 98786,
        streamId = 98827866,
        subject = "MyM",
        timestamp = 7877878L,
        messageText = "Hi"
    )

    val USER_NET_MESSAGE_WITH_EMOJIS = MessageObject(
    messageId = 65457,
    isMeMessage = false,
    avatarUrl = "",
    reactions = listOf(
    ReactionObject(
    emojiCode = "1",
    emojiName = "2",
    reactionType = "3",
    userId = 111
    )
    ),
    senderFullName = "Av An Am",
    senderId = 98786,
    streamId = 98827866,
    subject = "MyM",
    timestamp = 7877878L,
    messageText = "Hi"
    )
}