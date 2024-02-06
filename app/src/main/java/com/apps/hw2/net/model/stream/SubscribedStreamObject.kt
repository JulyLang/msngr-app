package com.apps.hw2.net.model.stream

import com.apps.hw2.net.model.TopicObject
import com.google.gson.annotations.SerializedName

data class SubscribedStreamObject (
    @SerializedName("stream_id")
    val stream_id: Int,

    @SerializedName("name")
    val name: String,

    var topics: List<TopicObject>?
)