package com.apps.hw2.profile.di

import com.apps.hw2.profile.usecase.FetchMyUserUseCase
import com.apps.hw2.profile.usecase.IFetchMyUserUseCase
import dagger.Binds
import dagger.Module

@Module(includes = [UserModule.BindsUseCases::class])
class UserModule {

    @Module
    interface BindsUseCases {

        @Binds
        fun bindSendMessageUseCase(fetchMyUserUseCase: FetchMyUserUseCase): IFetchMyUserUseCase
    }
}