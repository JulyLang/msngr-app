package com.apps.hw2.db.mapper

import com.apps.hw2.db.entity.streams.StreamsEntity
import com.apps.hw2.net.mapper.TopicsMapper
import com.apps.hw2.net.model.stream.StreamObject

class StreamsEntitiesMapper {
    fun mapStreamsEntitiesToStreamObjects(streamsEntities: List<StreamsEntity>): List<StreamObject> {
        return streamsEntities.map {
            StreamObject(
                it.id,
                it.streamName,
                TopicsMapper().mapTopicItemToTopicObject(it.topics)
            )
        }
    }
}