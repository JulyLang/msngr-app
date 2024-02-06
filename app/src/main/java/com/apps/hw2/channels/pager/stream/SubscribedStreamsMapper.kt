package com.apps.hw2.channels.pager.stream

import com.apps.hw2.net.model.stream.SubscribedStreamObject

class SubscribedStreamsMapper {
    fun mapSubscribedStreamsIStreams(subscribedStreams: List<SubscribedStreamObject>): List<IStreamItem> {
        return subscribedStreams.map { StreamItem(it.name, TopicsMapper.mapToTopics(it.topics)) }
    }
}