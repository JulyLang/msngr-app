package com.apps.hw2.net.model.stream

import com.apps.hw2.net.model.TopicObject

data class StreamObject(
    val stream_id: Int,
    val name: String,
    var topics: List<TopicObject>?
)