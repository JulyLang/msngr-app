package com.apps.hw2.channels.pager.stream

data class BindStreamItemArgs(
    val name: String,
    val isExpanded: Boolean,
    val topicsCounter: Int,
    val streamClickListener: StreamClickListener
) : BindViewHolderArgs