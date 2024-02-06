package com.apps.hw2.chat.elm

import com.apps.hw2.list.model.BaseMessage

data class State(
    val isLoading: Boolean = false,
    val messages: List<BaseMessage> = emptyList(),
    val showSendMessagePlane: Boolean = false
)