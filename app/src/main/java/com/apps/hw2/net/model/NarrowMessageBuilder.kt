package com.apps.hw2.net.model

object NarrowMessageBuilder {
    fun build(streamId: Int, topicName: String): String {
        return "[{\"operator\":\"stream\",\"operand\":$streamId},{\"operator\":\"topic\",\"operand\":\"$topicName\"}]"
    }
}
