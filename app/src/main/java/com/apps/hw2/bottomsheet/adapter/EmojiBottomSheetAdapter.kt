package com.apps.hw2.bottomsheet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.hw2.R
import com.apps.hw2.bottomsheet.EmojiBottomSheetViewHolder
import com.apps.hw2.emoji.EmojiItem

class EmojiBottomSheetAdapter(
    private val unicodeList: List<EmojiItem>,
    private val clickListener: EmojiClickListener? = null
) : RecyclerView.Adapter<EmojiBottomSheetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiBottomSheetViewHolder {
        return EmojiBottomSheetViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_emoji_bottom_sheet, parent, false),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: EmojiBottomSheetViewHolder, position: Int) {
        holder.onBind(emojiItem = unicodeList[position])
    }

    override fun getItemCount(): Int = unicodeList.size
}