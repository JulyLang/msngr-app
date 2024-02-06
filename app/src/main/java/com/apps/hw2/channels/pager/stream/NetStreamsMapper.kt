package com.apps.hw2.channels.pager.stream

import com.apps.hw2.net.model.stream.StreamObject

class NetStreamsMapper {
    fun mapNetStreamsToIStreams(netStreams: List<StreamObject>): List<IStreamItem> {
        return netStreams.map { StreamItem(it.name, TopicsMapper.mapToTopics(it.topics)) }
    }
}