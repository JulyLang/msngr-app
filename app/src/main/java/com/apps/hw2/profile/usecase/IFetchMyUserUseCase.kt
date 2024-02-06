package com.apps.hw2.profile.usecase

import io.reactivex.Single

interface IFetchMyUserUseCase {
    fun execute(): Single<Int>
}