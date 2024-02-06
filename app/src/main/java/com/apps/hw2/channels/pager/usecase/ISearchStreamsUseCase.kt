package com.apps.hw2.channels.pager.usecase

import io.reactivex.Observable

interface ISearchStreamsUseCase {
    fun execute(): Observable<String>
}