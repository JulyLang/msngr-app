package com.apps.hw2.list.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.apps.hw2.R
import com.apps.hw2.list.adapter.EmojiClickListener
import com.apps.hw2.list.adapter.MessageClickListener
import com.apps.hw2.list.adapter.PlusClickListener
import com.apps.hw2.list.model.BaseMessage
import com.apps.hw2.list.model.UserMessage
import com.apps.hw2.model.EmojiCounter
import com.apps.hw2.view.EmojiFlexBoxLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UserMessageViewHolder(
    itemView: View,
    private val messageClickListener: MessageClickListener? = null
) : BaseMessageViewHolder<UserMessage>(itemView) {

    private val userAvatar: ImageView
        get() = itemView.findViewById(R.id.userAvatar)
    private val contactNameTV: TextView
        get() = itemView.findViewById(R.id.contactNameTV)
    private val contactMessageTV: TextView
        get() = itemView.findViewById(R.id.contactMessageTV)
    private val emojiFlexBoxLayout: EmojiFlexBoxLayout
        get() = itemView.findViewById(R.id.emojiFlexBoxLayout)

    private val emojiClickListener = object : EmojiClickListener {
        override fun onEmojiClick(emojiName: String, emojiCode: String) {
            val message: BaseMessage = baseMessage ?: return
            messageClickListener?.onEmojiClick(message, emojiName, emojiCode)
        }
    }
    private val plusClickListener = object : PlusClickListener {
        override fun onClick() {
            val message: BaseMessage = baseMessage ?: return
            messageClickListener?.onLongClick(message)
        }
    }

    override fun render(message: UserMessage, payloads: List<Any>) {
        super.render(message, payloads)
        Glide
            .with(userAvatar)
            .load(message.userAvatar)
            .apply(RequestOptions.circleCropTransform())
            .into(userAvatar)
        contactNameTV.text = message.contactName
        contactMessageTV.text = message.contactMessage
        emojiFlexBoxLayout.emojiClickListener = emojiClickListener
        emojiFlexBoxLayout.plusClickListener = plusClickListener
        messageClickListener?.let { listener ->
            itemView.setOnLongClickListener {
                listener.onLongClick(message)
                true
            }
        }
        updateEmojis(message.emojis)
    }

    private fun updateEmojis(emojis: List<EmojiCounter>) {
        emojiFlexBoxLayout.updateEmojis(emojis)
    }
}