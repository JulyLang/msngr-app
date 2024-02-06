package com.apps.hw2.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import com.apps.hw2.R
import com.apps.hw2.list.diff.MessagesDiffCallback
import com.apps.hw2.list.holder.BaseMessageViewHolder
import com.apps.hw2.list.holder.DateMessageViewHolder
import com.apps.hw2.list.holder.MyUserMessageViewHolder
import com.apps.hw2.list.holder.UserMessageViewHolder
import com.apps.hw2.list.model.*

class MessagesAdapter(
    private val context: Context,
    private val messageClickListener: MessageClickListener? = null
) : ListAdapter<BaseMessage, BaseMessageViewHolder<out BaseMessage>>(
    AsyncDifferConfig.Builder(MessagesDiffCallback).build()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseMessageViewHolder<out BaseMessage> {
        return when (MessageType.fromInt(viewType)) {
            MessageType.DATE -> DateMessageViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_date_message, parent, false)
            )
            MessageType.MY_USER -> MyUserMessageViewHolder(
                LayoutInflater.from(context).inflate(R.layout.my_user_message_item_rv, parent, false),
                messageClickListener
            )
            MessageType.USER -> UserMessageViewHolder(
                LayoutInflater.from(context).inflate(R.layout.user_message_item_rv, parent, false),
                messageClickListener
            )
        }
    }

    override fun onBindViewHolder(
        holder: BaseMessageViewHolder<out BaseMessage>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        internalOnBindViewHolder(holder, position, payloads)
    }

    override fun onBindViewHolder(holder: BaseMessageViewHolder<out BaseMessage>, position: Int) {
        onBindViewHolder(holder, position, mutableListOf())
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).messageType.type
    }

    private fun internalOnBindViewHolder(
        holder: BaseMessageViewHolder<out BaseMessage>,
        position: Int,
        payloads: List<Any>
    ) {
        val message = getItem(position)
        when (message.messageType) {
            MessageType.DATE -> (holder as DateMessageViewHolder).render(
                message as DateMessage,
                payloads
            )
            MessageType.MY_USER -> (holder as MyUserMessageViewHolder).render(
                message as MyUserMessage,
                payloads
            )
            MessageType.USER -> (holder as UserMessageViewHolder).render(
                message as UserMessage,
                payloads
            )
        }
    }
}