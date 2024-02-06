package com.apps.hw2.channels.pager.stream

class IStreamsMapper {
    fun mapIStreamsToStreamItemRecyclerViewAdapters(
        streams: List<IStreamItem>,
        topicClickListener: TopicClickListener
    ): List<StreamItemRecyclerViewAdapter> {
        return streams.map { StreamItemRecyclerViewAdapter(it, topicClickListener) }
    }
}