package com.apps.hw2.channels.di

import com.apps.hw2.channels.pager.repository.*
import com.apps.hw2.channels.pager.usecase.*
import dagger.Binds
import dagger.Module

@Module(includes = [ChannelsModule.BindsUseCases::class])
class ChannelsModule {

    @Module
    interface BindsUseCases {

        @ChannelsScope
        @Binds
        fun bindObserveSubscribedStreamsUseCase(
            observeSubscribedStreamsUseCase: ObserveSubscribedStreamsUseCase
        ): IObserveStreamsUseCase

        @ChannelsScope
        @Binds
        fun bindObserveAllStreamsUseCase(
            observeAllStreamsUseCase: ObserveAllStreamsUseCase
        ): IObserveStreamsUseCase

        @ChannelsScope
        @Binds
        fun bindSyncSubscribedStreamsUseCase(
            syncSubscribedStreamsUseCase: SyncSubscribedStreamsUseCase
        ): ISyncStreamsUseCase

        @ChannelsScope
        @Binds
        fun bindSyncAllStreamsUseCase(
            syncAllStreamsUseCase: SyncAllStreamsUseCase
        ): ISyncStreamsUseCase

        @ChannelsScope
        @Binds
        fun bindSearchStreamsUseCase(
            searchStreamsUseCase: SearchStreamsUseCase
        ): ISearchStreamsUseCase

        @ChannelsScope
        @Binds
        fun bindStreamsLocalDataStore(
            streamsLocalDataStore: StreamsLocalDataStore
        ): IStreamsLocalDataStore

        @ChannelsScope
        @Binds
        fun bindStreamsRemoteDataStore(
            streamsRemoteDataStore: StreamsRemoteDataStore
        ): IStreamsRemoteDataStore

        @ChannelsScope
        @Binds
        fun bindStreamsRepository(
            streamsRepository: StreamsRepository
        ): IStreamsRepository
    }
}