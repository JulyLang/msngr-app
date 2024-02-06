package com.apps.hw2.channels.pager.stream

interface TopicClickListener {
    fun onTopicClicked(streamId: Int, topicName: String)
}