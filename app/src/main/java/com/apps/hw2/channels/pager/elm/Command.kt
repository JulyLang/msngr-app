package com.apps.hw2.channels.pager.elm

sealed class Command {
    object ObserveStreams : Command()
    object SyncStreams : Command()
    object ObserveSearch : Command()
}
