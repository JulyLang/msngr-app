package com.apps.hw2.chat.usecase

import io.reactivex.Single

interface IAddEmojiReactionUseCase {
    fun execute(
        messageId: Int,
        emojiName: String,
        emojiCode: String?,
        reactionType: String?
    ): Single<Boolean>
}