package com.apps.hw2.list.adapter

import com.apps.hw2.list.model.BaseMessage

interface MessageClickListener {
   fun onClick(message: BaseMessage)
   fun onLongClick(message: BaseMessage)
   fun onEmojiClick(message: BaseMessage, emojiName: String, emojiCode: String)
}