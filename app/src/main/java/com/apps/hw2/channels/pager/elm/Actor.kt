package com.apps.hw2.channels.pager.elm

import com.apps.hw2.channels.pager.usecase.IObserveStreamsUseCase
import com.apps.hw2.channels.pager.usecase.ISyncStreamsUseCase
import com.apps.hw2.channels.pager.stream.IStreamsMapper
import com.apps.hw2.channels.pager.stream.TopicClickListener
import com.apps.hw2.channels.pager.usecase.ISearchStreamsUseCase
import io.reactivex.Observable
import vivid.money.elmslie.core.ActorCompat

class Actor(
    private val searchStreamsUseCase: ISearchStreamsUseCase,
    private val observeStreamsUseCase: IObserveStreamsUseCase,
    private val syncStreamsUseCase: ISyncStreamsUseCase,
    private val topicClickListener: TopicClickListener
) : ActorCompat<Command, Event.Internal> {
    override fun execute(command: Command): Observable<Event.Internal> = when (command) {

        is Command.ObserveSearch -> searchStreamsUseCase.execute()
            .mapEvents(Event.Internal::SearchStreamsSuccess, Event.Internal.SearchStreamsError)

        is Command.ObserveStreams -> observeStreamsUseCase.execute()
            .map { IStreamsMapper().mapIStreamsToStreamItemRecyclerViewAdapters(it, topicClickListener) }
            .mapEvents(Event.Internal::StreamsLoadingSuccess, Event.Internal.StreamsLoadingError)

        is Command.SyncStreams -> syncStreamsUseCase.execute()
            .mapEvents(Event.Internal::StreamsSyncSuccess, Event.Internal.StreamsSyncError)
    }
}