package com.apps.hw2.channels.pager.stream

data class StreamItem(
    override val name: String,
    override val topics: List<TopicItem>
): IStreamItem {
    override fun isFindByInput(input: String): Boolean {
        return name.contains(input, ignoreCase = true)
    }
}