package com.apps.hw2.channels.pager.usecase

import io.reactivex.Single

interface ISyncStreamsUseCase {
    fun execute(): Single<Boolean>
}