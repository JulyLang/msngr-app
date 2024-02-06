package com.apps.hw2.db.mapper

import com.apps.hw2.db.entity.streams.SubscribedStreamsEntity
import com.apps.hw2.net.mapper.TopicsMapper
import com.apps.hw2.net.model.stream.SubscribedStreamObject

class SubscribedStreamsEntitiesMapper {
    fun mapSubscribedStreamsEntitiesToSubscribedStreamObjects(subscribedStreamsEntities: List<SubscribedStreamsEntity>): List<SubscribedStreamObject> {
        return subscribedStreamsEntities.map {
            SubscribedStreamObject(
                stream_id = it.id,
                name = it.streamName,
                topics = TopicsMapper().mapTopicItemToTopicObject(it.topics)
            )
        }
    }
}