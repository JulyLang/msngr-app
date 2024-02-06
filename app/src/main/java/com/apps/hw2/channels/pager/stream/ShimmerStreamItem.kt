package com.apps.hw2.channels.pager.stream

class ShimmerStreamItem(
    override val name: String = "ShimmerStreamItem",
    override val topics: List<TopicItem> = emptyList()
): IStreamItem {
    override fun isFindByInput(input: String): Boolean {
        return false
    }
}