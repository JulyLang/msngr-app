package com.apps.hw2.list.model

enum class MessageType(val type: Int) {
    DATE(type = 1),
    MY_USER(type = 2),
    USER(type = 3);

    companion object {
        fun fromInt(value: Int) = values().first { it.type == value }
    }
}