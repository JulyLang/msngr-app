package com.apps.hw2.channels.pager.elm

import android.util.Log
import vivid.money.elmslie.core.store.dsl_reducer.ScreenDslReducer

class Reducer : ScreenDslReducer<Event, Event.Ui, Event.Internal, State, Effect, Command>(
    Event.Ui::class,
    Event.Internal::class
) {
    override fun Result.internal(event: Event.Internal): Any = when (event) {
        is Event.Internal.StreamsLoadingSuccess -> {
            state { copy(isLoading = false, streams = event.streams) }
        }
        is Event.Internal.StreamsLoadingError -> {
            state { copy(isLoading = false) }
            effects { +Effect.ShowError }
        }
        is Event.Internal.StreamsSyncSuccess -> Log.d("Reducer", "StreamsSyncSuccess")
        is Event.Internal.StreamsSyncError -> Log.e("Reducer", "StreamsSyncError")
        is Event.Internal.SearchStreamsSuccess -> state { copy(searchQuery = event.searchQuery) }
        is Event.Internal.SearchStreamsError -> Log.e("Reducer", "SearchStreamsError")
    }

    override fun Result.ui(event: Event.Ui): Any = when (event) {
        is Event.Ui.Init -> {
            state { copy(isLoading = true) }
            commands { +Command.ObserveStreams }
            commands { +Command.SyncStreams }
            commands { +Command.ObserveSearch }
        }
        is Event.Ui.OnTopicClicked -> {
            effects {
                +Effect.NavigateToChat(
                    streamId = event.streamId,
                    topicName = event.topicName
                )
            }
        }
    }
}