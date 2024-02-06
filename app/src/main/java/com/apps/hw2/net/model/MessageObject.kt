package com.apps.hw2.net.model

import com.google.gson.annotations.SerializedName

data class MessageObject (
    @SerializedName("id")
    val messageId: Int,

    @SerializedName("is_me_message")
    val isMeMessage: Boolean,

    @SerializedName("avatar_url")
    val avatarUrl: String?,

    @SerializedName("reactions")
    val reactions: List<ReactionObject>,

    @SerializedName("sender_full_name")
    val senderFullName: String,

    @SerializedName("sender_id")
    val senderId: Int,

    @SerializedName("stream_id")
    val streamId: Int,

    @SerializedName("subject")
    val subject: String,

    @SerializedName("timestamp")
    val timestamp: Long,

    @SerializedName("content")
    val messageText: String
)