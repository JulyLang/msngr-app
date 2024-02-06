package com.apps.hw2.channels.pager.stream

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.apps.hw2.ext.dpToPx

class StreamItemDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        when (parent.getChildAdapterPosition(view)) {
            RecyclerView.NO_POSITION -> return
            0 -> outRect.set(0, 0, 0, 0)
            else -> outRect.set(0, view.dpToPx(ITEM_PADDING_TOP).toInt(), 0, 0)
        }
    }

    companion object {
        const val ITEM_PADDING_TOP = 2
    }
}