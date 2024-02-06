package com.apps.hw2.net.model

import com.google.gson.annotations.SerializedName

data class Reaction (
    @SerializedName("emoji_name")
    val emojiName: String,

    @SerializedName("emoji_code")
    val emojiCode: String,

    @SerializedName("reaction_type")
    val reactionType: String,

    @SerializedName("user_id")
    val userId: Int
)