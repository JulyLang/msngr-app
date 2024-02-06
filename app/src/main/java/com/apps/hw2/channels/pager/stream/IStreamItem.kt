package com.apps.hw2.channels.pager.stream

interface IStreamItem {
    val name: String
    val topics: List<TopicItem>

    fun isFindByInput(input: String): Boolean
}