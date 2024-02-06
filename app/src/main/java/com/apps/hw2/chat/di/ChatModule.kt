package com.apps.hw2.chat.di

import com.apps.hw2.chat.usecase.*
import dagger.Binds
import dagger.Module

@Module(includes = [ChatModule.BindsUseCases::class])
class ChatModule {

    @Module
    interface BindsUseCases {

        @ChatScope
        @Binds
        fun bindObserveMessagesUseCase(observeMessagesUseCase: ObserveMessagesUseCase): IObserveMessagesUseCase

        @ChatScope
        @Binds
        fun bindSyncMessagesUseCase(syncMessagesUseCase: SyncMessagesUseCase): ISyncMessagesUseCase

        @ChatScope
        @Binds
        fun bindSendMessageUseCase(sendMessageUseCase: SendMessageUseCase): ISendMessageUseCase

        @ChatScope
        @Binds
        fun bindAddEmojiReactionUseCase(addEmojiReactionUseCase: AddEmojiReactionUseCase): IAddEmojiReactionUseCase

        @ChatScope
        @Binds
        fun bindDeleteEmojiReactionUseCase(deleteEmojiReactionUseCase: DeleteEmojiReactionUseCase): IDeleteEmojiReactionUseCase
    }
}