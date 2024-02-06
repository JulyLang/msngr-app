package com.apps.hw2.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.core.view.marginRight
import com.apps.hw2.R
import com.apps.hw2.ext.*

class UserMessageViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewGroup(context, attrs) {

    private val bgPaint = Paint().apply {
        color = resources.getColor(R.color.message_bg_color)
    }

    private val userAvatar: ImageView
        get() = findViewById(R.id.userAvatar)
    private val userName: TextView
        get() = findViewById(R.id.contactNameTV)
    private val message: TextView
        get() = findViewById(R.id.contactMessageTV)
    private val emojiFlexBoxLayout: EmojiFlexBoxLayout
        get() = findViewById(R.id.emojiFlexBoxLayout)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildWithMargins(userAvatar, widthMeasureSpec, 0, heightMeasureSpec, 0)
        measureChildWithMargins(
            userName,
            widthMeasureSpec,
            dpToPxFromRes(R.dimen.name_and_message_space_left).toInt(),
            heightMeasureSpec,
            0
        )
        measureChildWithMargins(
            message,
            widthMeasureSpec,
            dpToPxFromRes(R.dimen.name_and_message_space_left).toInt(),
            heightMeasureSpec,
            0
        )
        measureChildWithMargins(
            emojiFlexBoxLayout,
            widthMeasureSpec,
            userAvatar.measuredWidth,
            heightMeasureSpec,
            0
        )

        val contentWidth = userAvatar.measuredWidthWithMargins + maxOf(
            userName.measuredWidthWithMargins,
            message.measuredWidthWithMargins,
            emojiFlexBoxLayout.measuredWidthWithMargins
        )
        val contentHeight =
            userName.measuredHeightWithMargins + message.measuredHeightWithMargins +
                    emojiFlexBoxLayout.measuredHeightWithMargins

        setMeasuredDimension(
            resolveSize(contentWidth, widthMeasureSpec),
            resolveSize(contentHeight, heightMeasureSpec)
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        userAvatar.layout(
            userAvatar.marginLeft,
            userAvatar.marginTop,
            userAvatar.marginLeft + userAvatar.measuredWidth,
            userAvatar.marginTop + userAvatar.measuredHeight
        )

        val userNameLeft = userAvatar.right + userAvatar.marginRight + userName.marginLeft

        userName.layout(
            userAvatar.right + userName.marginLeft,
            userName.marginTop,
            userNameLeft + userName.measuredWidth,
            userName.marginTop + userName.measuredHeight
        )

        message.layout(
            userAvatar.right + message.marginLeft,
            userName.marginTop + userName.measuredHeight + message.marginTop,
            userNameLeft + message.measuredWidth,
            userName.marginTop + userName.measuredHeight + userName.marginBottom +
                    message.marginTop + message.measuredHeight
        )

        emojiFlexBoxLayout.layout(
            userAvatar.right + emojiFlexBoxLayout.marginLeft,
            userName.marginTop + userName.measuredHeight + message.marginTop
                    + message.measuredHeight + message.marginBottom,
            userNameLeft + emojiFlexBoxLayout.measuredWidth,
            userName.marginTop + userName.measuredHeight + userName.marginBottom
                    + message.marginTop + message.measuredHeight + message.marginBottom
                    + emojiFlexBoxLayout.marginTop + emojiFlexBoxLayout.measuredHeight
                    + emojiFlexBoxLayout.marginBottom
        )
    }

    override fun dispatchDraw(canvas: Canvas) {
        canvas.drawRoundRect(
            userName.left - dpToPxFromRes(R.dimen.name_and_message_bg_padding_left),
            0f,
            maxOf(
                userName.right,
                message.right
            ).toFloat() + dpToPxFromRes(R.dimen.name_and_message_bg_padding_left),
            (userName.measuredHeightWithMargins + message.measuredHeightWithMargins).toFloat(),
            dpToPxFromRes(R.dimen.bg_corner_radius),
            dpToPxFromRes(R.dimen.bg_corner_radius),
            bgPaint
        )
        super.dispatchDraw(canvas)
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
}