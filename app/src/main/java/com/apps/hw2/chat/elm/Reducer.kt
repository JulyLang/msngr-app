package com.apps.hw2.chat.elm

import android.util.Log
import vivid.money.elmslie.core.store.dsl_reducer.ScreenDslReducer

class Reducer : ScreenDslReducer<Event, Event.Ui, Event.Internal, State, Effect, Command>(
    Event.Ui::class,
    Event.Internal::class
) {
    override fun Result.internal(event: Event.Internal): Any = when (event) {
        is Event.Internal.MessagesLoadingSuccess -> {
            state { copy(isLoading = false, messages = event.messages) }
        }
        is Event.Internal.MessagesLoadingError -> {
            state { copy(isLoading = false) }
            effects { +Effect.ShowError }
        }
        is Event.Internal.MessagesSyncSuccess -> Log.d("Reducer", "MessagesSyncSuccess")
        is Event.Internal.MessagesSyncError -> Log.e("Reducer", "MessagesSyncError")
        is Event.Internal.SendMessageSuccess -> Log.d("Reducer", "SendMessageSuccess")
        is Event.Internal.SendMessageError -> Log.e("Reducer", "SendMessageError")
    }

    override fun Result.ui(event: Event.Ui): Any = when (event) {
        is Event.Ui.Init -> {
            state { copy(isLoading = true) }
            commands { +Command.ObserveMessages }
            commands { +Command.SyncMessages }
        }
        is Event.Ui.OnMessageClicked -> {
            effects { +Effect.OnClick(message = event.message) }
        }
        is Event.Ui.OnMessageLongClicked -> {
            effects { +Effect.OnLongClick(message = event.message) }
        }
        is Event.Ui.OnMessageTextChanged -> {
            state { copy(showSendMessagePlane = !event.text.isNullOrBlank()) }
        }
        is Event.Ui.LoadNewMessagesPage -> {
            state { copy(isLoading = true) }
            commands { +Command.SyncMessages }
        }
        is Event.Ui.SendNewMessage -> {
            commands { +Command.SendNewMessage(messageText = event.messageText) }
        }
    }
}