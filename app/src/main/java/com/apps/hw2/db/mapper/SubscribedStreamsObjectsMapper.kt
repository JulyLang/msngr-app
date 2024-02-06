package com.apps.hw2.db.mapper

import com.apps.hw2.db.entity.streams.SubscribedStreamsEntity
import com.apps.hw2.net.mapper.TopicsMapper
import com.apps.hw2.net.model.stream.SubscribedStreamObject

class SubscribedStreamsObjectsMapper {
    fun mapSubscribedStreamsObjectsToSubscribedStreamsEntities(subscribedStreamsObjects: List<SubscribedStreamObject>): List<SubscribedStreamsEntity> {
        return subscribedStreamsObjects.map {
            SubscribedStreamsEntity(
                id = it.stream_id,
                streamName = it.name,
                topics = TopicsMapper().mapTopicObjectToTopicItem(it.topics ?: emptyList())
            )
        }
    }
}