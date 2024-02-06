package com.apps.hw2.channels.pager.stream

import androidx.recyclerview.widget.RecyclerView

class StreamItemInfo : RecyclerView.ItemAnimator.ItemHolderInfo() {

    var arrowRotation: Float = 0F

    override fun setFrom(holder: RecyclerView.ViewHolder): RecyclerView.ItemAnimator.ItemHolderInfo {
        if (holder is StreamItemViewHolder) {
            arrowRotation = holder.arrow.rotation
        }
        return super.setFrom(holder)
    }
}