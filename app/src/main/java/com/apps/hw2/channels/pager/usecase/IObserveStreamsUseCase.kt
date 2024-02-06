package com.apps.hw2.channels.pager.usecase

import com.apps.hw2.channels.pager.stream.IStreamItem
import io.reactivex.Observable

interface IObserveStreamsUseCase {
    fun execute(): Observable<List<IStreamItem>>
}