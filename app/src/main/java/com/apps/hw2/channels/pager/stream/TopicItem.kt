package com.apps.hw2.channels.pager.stream

data class TopicItem(
    val streamId: Int,
    val topicName: String,
    val messagesCount: Int
)