package com.apps.hw2.channels.pager.stream

import com.apps.hw2.net.model.TopicObject

object TopicsMapper {
    fun mapToTopics(topics: List<TopicObject>?): List<TopicItem> {
        return topics?.map { TopicItem(
            streamId = it.streamId,
            topicName = it.topicName,
            messagesCount = -1
        ) } ?: emptyList()
    }
}