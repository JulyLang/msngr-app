package com.apps.hw2.net.model

data class SendMessageResult(
    val id: Int?,
    val msg: String,
    val result: String,
    val code: String?,
    val stream: String?
)