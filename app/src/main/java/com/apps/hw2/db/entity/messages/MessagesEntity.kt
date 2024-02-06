package com.apps.hw2.db.entity.messages

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessagesEntity (

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "is_me_message")
    val isMeMessage: Boolean,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,

    @ColumnInfo(name = "sender_full_name")
    val senderFullName: String,

    @ColumnInfo(name = "sender_id")
    val senderId: Int,

    @ColumnInfo(name = "stream_id")
    val streamId: Int,

    @ColumnInfo(name = "subject")
    val subject: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "reactions")
    val reactions: List<Reaction>
) {
    fun isMyMessage(userId: Int): Boolean {
        return senderId == userId
    }
}