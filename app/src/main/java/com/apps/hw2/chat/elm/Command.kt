package com.apps.hw2.chat.elm

sealed class Command {
    object ObserveMessages : Command()
    object SyncMessages : Command()
    data class SendNewMessage(val messageText: String) : Command()
}