package com.apps.hw2.list.holder

import android.view.View
import android.widget.TextView
import com.apps.hw2.R
import com.apps.hw2.list.adapter.MessageClickListener
import com.apps.hw2.list.model.MyUserMessage
import com.apps.hw2.model.EmojiCounter
import com.apps.hw2.view.EmojiFlexBoxLayout

class MyUserMessageViewHolder(
    itemView: View,
    private val messageClickListener: MessageClickListener? = null
) : BaseMessageViewHolder<MyUserMessage>(itemView) {

    private val contactMessageTV: TextView
        get() = itemView.findViewById(R.id.contactMessageTV)
    private val emojiFlexBoxLayout: EmojiFlexBoxLayout
        get() = itemView.findViewById(R.id.emojiFlexBoxLayout)

    override fun render(message: MyUserMessage, payloads: List<Any>) {
        super.render(message, payloads)
        contactMessageTV.text = message.contactMessage
        updateEmojis(message.emojis)
        messageClickListener?.let { listener ->
            itemView.setOnLongClickListener {
                listener.onLongClick(message)
                true
            }
        }
    }

    private fun updateEmojis(emojis: List<EmojiCounter>) {
        emojiFlexBoxLayout.updateEmojis(emojis)
    }
}