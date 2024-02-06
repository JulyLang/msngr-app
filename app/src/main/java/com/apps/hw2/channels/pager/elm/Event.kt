package com.apps.hw2.channels.pager.elm

import com.apps.hw2.channels.pager.stream.StreamItemRecyclerViewAdapter

sealed class Event {
    sealed class Ui : Event() {
        object Init : Ui()
        data class OnTopicClicked(val streamId: Int, val topicName: String) : Ui()
    }

    sealed class Internal : Event() {
        data class StreamsLoadingSuccess(val streams: List<StreamItemRecyclerViewAdapter>) : Internal()
        object StreamsLoadingError : Internal()

        data class StreamsSyncSuccess(val result: Boolean) : Internal()
        object StreamsSyncError : Internal()

        data class SearchStreamsSuccess(val searchQuery: String) : Internal()
        object SearchStreamsError : Internal()
    }
}
