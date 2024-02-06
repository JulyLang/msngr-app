package com.apps.hw2.list.diff

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.apps.hw2.list.model.BaseMessage

object MessagesDiffCallback : DiffUtil.ItemCallback<BaseMessage>() {

    const val TAG = ""

    override fun areItemsTheSame(oldItem: BaseMessage, newItem: BaseMessage): Boolean {
        return oldItem.messageId == newItem.messageId
    }

    override fun areContentsTheSame(oldItem: BaseMessage, newItem: BaseMessage): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: BaseMessage, newItem: BaseMessage): Any? {
        if (oldItem.messageId == newItem.messageId) {
            return if (oldItem == newItem) {
                super.getChangePayload(oldItem, newItem)
            } else {
                val diff = Bundle()
                diff.putBoolean(TAG, true)
                diff
            }
        }
        return super.getChangePayload(oldItem, newItem)
    }
}