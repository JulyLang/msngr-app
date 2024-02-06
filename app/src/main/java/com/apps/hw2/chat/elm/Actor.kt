package com.apps.hw2.chat.elm

import com.apps.hw2.chat.usecase.IObserveMessagesUseCase
import com.apps.hw2.chat.usecase.ISendMessageUseCase
import com.apps.hw2.chat.usecase.ISyncMessagesUseCase
import io.reactivex.Observable
import vivid.money.elmslie.core.ActorCompat

class Actor(
    private val streamId: Int,
    private val topicName: String,
    private val observeMessagesUseCase: IObserveMessagesUseCase,
    private val syncMessagesUseCase: ISyncMessagesUseCase,
    private val sendMessageUseCase: ISendMessageUseCase
) : ActorCompat<Command, Event.Internal> {

    override fun execute(command: Command): Observable<Event.Internal> = when (command) {
        is Command.ObserveMessages -> observeMessagesUseCase.execute(streamId, topicName)
            .mapEvents(Event.Internal::MessagesLoadingSuccess, Event.Internal.MessagesLoadingError)

        is Command.SyncMessages -> syncMessagesUseCase.execute(streamId, topicName)
            .mapEvents(Event.Internal::MessagesSyncSuccess, Event.Internal.MessagesSyncError)

        is Command.SendNewMessage -> sendMessageUseCase.execute(streamId, topicName, command.messageText)
            .mapEvents(Event.Internal::SendMessageSuccess, Event.Internal.SendMessageError)
    }
}