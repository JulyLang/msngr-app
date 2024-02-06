package com.apps.hw2.chat.usecase

import io.reactivex.Single

interface ISendMessageUseCase {
    fun execute(streamId: Int, topicName: String, messageText: String): Single<Boolean>
}