package com.apps.hw2.list.model

abstract class BaseMessage(
    val messageId: Int,
    val messageType: MessageType,
    open val dateCreated: Long
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseMessage) return false

        if (messageId != other.messageId) return false
        if (messageType != other.messageType) return false
        if (dateCreated != other.dateCreated) return false

        return true
    }

    override fun hashCode(): Int {
        var result = messageId.hashCode()
        result = 31 * result + messageType.hashCode()
        result = 31 * result + dateCreated.hashCode()
        return result
    }
}