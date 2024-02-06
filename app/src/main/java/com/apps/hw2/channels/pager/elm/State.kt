package com.apps.hw2.channels.pager.elm

import com.apps.hw2.channels.pager.stream.StreamItemRecyclerViewAdapter

data class State(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val streams: List<StreamItemRecyclerViewAdapter> = emptyList()
)
