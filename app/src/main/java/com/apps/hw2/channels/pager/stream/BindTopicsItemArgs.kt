package com.apps.hw2.channels.pager.stream

data class BindTopicsItemArgs(
    val streamId: Int,
    val topicName: String,
    val counter: Int,
    val pos: Int,
    val topicClickListener: TopicClickListener
) : BindViewHolderArgs