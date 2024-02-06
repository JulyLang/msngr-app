package com.apps.hw2.chat.usecase

import io.reactivex.Single

interface ISyncMessagesUseCase {
    fun execute(streamId: Int, topicName: String): Single<Boolean>
}