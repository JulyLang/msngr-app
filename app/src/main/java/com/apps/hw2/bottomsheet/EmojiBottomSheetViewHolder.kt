package com.apps.hw2.bottomsheet

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.hw2.R
import com.apps.hw2.bottomsheet.adapter.EmojiClickListener
import com.apps.hw2.emoji.EmojiItem
import com.apps.hw2.ext.getAndroidReadableEmoji

class EmojiBottomSheetViewHolder(
    itemView: View,
    private val clickListener: EmojiClickListener? = null
) : RecyclerView.ViewHolder(itemView) {

    private var emojiItem: EmojiItem? = null

    init {
        itemView.setOnClickListener {
            emojiItem?.let { clickListener?.onClick(it) }
        }
    }

    fun onBind(emojiItem: EmojiItem) {
        this.emojiItem = emojiItem
        itemView.findViewById<TextView>(R.id.emojiTextView).text = emojiItem.code.getAndroidReadableEmoji()
    }
}