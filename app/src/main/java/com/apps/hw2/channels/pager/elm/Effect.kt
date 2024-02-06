package com.apps.hw2.channels.pager.elm

sealed class Effect {
    object ShowError : Effect()
    data class NavigateToChat(val streamId: Int, val topicName: String) : Effect()
}
