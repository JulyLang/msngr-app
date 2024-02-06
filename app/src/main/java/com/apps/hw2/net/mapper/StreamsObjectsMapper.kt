package com.apps.hw2.net.mapper

import com.apps.hw2.db.entity.streams.StreamsEntity
import com.apps.hw2.net.model.stream.StreamObject

class StreamsObjectsMapper {
    fun mapStreamsObjectsToStreamsEntities(streamObjects: List<StreamObject>): List<StreamsEntity> {
        return streamObjects.map {
            StreamsEntity(
                it.stream_id,
                it.name,
                TopicsMapper().mapTopicObjectToTopicItem(it.topics ?: emptyList())
            )
        }
    }
}