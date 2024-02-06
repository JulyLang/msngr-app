package com.apps.hw2.net.model

import com.google.gson.annotations.SerializedName

data class TopicObject(
    //internal
    var streamId: Int,

    @SerializedName("name")
    val topicName: String,

    @SerializedName("max_id")
    val max_id: Int,
)
