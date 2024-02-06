package com.apps.hw2.net.mapper

import com.apps.hw2.channels.pager.stream.TopicItem
import com.apps.hw2.net.model.TopicObject

class TopicsMapper {
    fun mapTopicObjectToTopicItem(topics: List<TopicObject>): List<TopicItem> {
        return topics.map {
            TopicItem(
                streamId = it.streamId,
                topicName = it.topicName,
                messagesCount = -1
            )
        }
    }

    fun mapTopicItemToTopicObject(topics: List<TopicItem>): List<TopicObject> {
        return topics.map {
            TopicObject(
                streamId = it.streamId,
                topicName = it.topicName,
                max_id = -1
            )
        }
    }
}