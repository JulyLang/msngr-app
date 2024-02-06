package com.apps.hw2.chat.usecase

import com.apps.hw2.list.model.BaseMessage
import io.reactivex.Observable

interface IObserveMessagesUseCase {
    fun execute(streamId: Int, topicName: String): Observable<List<BaseMessage>>
}