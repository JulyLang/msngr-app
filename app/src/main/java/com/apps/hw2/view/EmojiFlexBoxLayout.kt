package com.apps.hw2.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import com.apps.hw2.R
import com.apps.hw2.ext.measuredHeightWithMargins
import com.apps.hw2.ext.measuredWidthWithMargins
import com.apps.hw2.list.adapter.EmojiClickListener
import com.apps.hw2.list.adapter.PlusClickListener
import com.apps.hw2.model.EmojiCounter

class EmojiFlexBoxLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewGroup(context, attrs) {

    var plusClickListener: PlusClickListener? = null
    var emojiClickListener: EmojiClickListener? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val maxAllowedWidth = MeasureSpec.getSize(widthMeasureSpec)

        var filledWidth = 0
        var filledHeight = 0

        var rowTotalWidth = 0
        var maxRowHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0)

            val childMeasuredWidth = child.measuredWidthWithMargins
            if (rowTotalWidth + childMeasuredWidth > maxAllowedWidth) {
                filledWidth = maxOf(filledWidth, rowTotalWidth)
                filledHeight += maxRowHeight
                maxRowHeight = 0
                rowTotalWidth = childMeasuredWidth
                if (i == childCount - 1) {
                    filledHeight += maxRowHeight
                }
            } else {
                rowTotalWidth += childMeasuredWidth
                maxRowHeight = maxOf(maxRowHeight, child.measuredHeightWithMargins)
                if (filledHeight == 0) {
                    filledHeight = maxRowHeight
                }
                if (filledWidth == 0 && i == childCount - 1) {
                    filledWidth = rowTotalWidth
                }
            }
        }

        setMeasuredDimension(
            resolveSize(filledWidth, widthMeasureSpec),
            resolveSize(filledHeight, heightMeasureSpec)
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val maxAllowedWidth = measuredWidth

        var currentTop = 0
        var currentLeft = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)

            if (currentLeft + child.marginLeft + child.measuredWidth + child.marginRight > maxAllowedWidth) {
                currentLeft = 0
                currentTop += child.marginTop + child.measuredHeight + child.marginBottom
            }

            child.layout(
                currentLeft + child.marginLeft,
                currentTop + child.marginTop + marginTop,
                currentLeft + child.measuredWidth + child.marginLeft,
                currentTop + child.measuredHeight + child.marginTop + marginTop
            )
            currentLeft += child.marginLeft + child.measuredWidth + child.marginRight
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun checkLayoutParams(p: LayoutParams): Boolean {
        return p is MarginLayoutParams
    }

    override fun generateLayoutParams(p: LayoutParams): LayoutParams {
        return MarginLayoutParams(p)
    }

    fun updateEmojis(emojis: List<EmojiCounter>) {
        removeAllViews()
        if (emojis.isNotEmpty()) {
            for (emoji in emojis) {
                val emojiCounterTextViewGroup = LayoutInflater.from(context)
                    .inflate(R.layout.item_emoji_counter_viewgroup, this, false)
                (emojiCounterTextViewGroup as EmojiCounterTextViewGroup).update(emoji)
                emojiCounterTextViewGroup.emojiClickListener = emojiClickListener
                addView(emojiCounterTextViewGroup)
            }
            val plusView = LayoutInflater.from(context)
                .inflate(R.layout.item_plus_view, this, false)
            plusView.setOnClickListener { plusClickListener?.onClick() }
            addView(plusView)
        }
        requestLayout()
    }
}