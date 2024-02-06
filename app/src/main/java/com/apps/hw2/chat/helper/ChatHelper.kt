package com.apps.hw2.chat.helper

import com.apps.hw2.list.model.BaseMessage
import io.reactivex.Observable

object ChatHelper {
    fun subscribeMessagesObservable(): Observable<List<BaseMessage>> {
        return Observable.just(emptyList())
    }

    fun syncMessagesFromNet(): Observable<Boolean> {
        return Observable.just(true)
    }
}