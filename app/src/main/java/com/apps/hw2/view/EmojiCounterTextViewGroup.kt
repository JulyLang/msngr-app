package com.apps.hw2.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.TextView
import com.apps.hw2.R
import com.apps.hw2.ext.*
import com.apps.hw2.list.adapter.EmojiClickListener
import com.apps.hw2.model.EmojiCounter

class EmojiCounterTextViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewGroup(context, attrs) {

    var emojiClickListener: EmojiClickListener? = null

    private var emojiName = ""
    private var emojiCode = ""
    private var emoji = ""
    private var counter = ""

    private val bgPaint = Paint().apply {
        isAntiAlias = true
        color = resources.getColor(R.color.emoji_bg_color_normal)
    }

    private val emojiTextView: TextView
        get() = findViewById(R.id.emojiTV)
    private val counterTextView: TextView
        get() = findViewById(R.id.counterTV)

    private val counterTextViewPadding: Int = dpToPx(2).toInt()

    init {
        inflate(context, R.layout.text_view_emoji_counter, this)

        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.EmojiCounterTextViewGroup)

        emoji = if (isInEditMode) {
            resources.getString(R.string.emoji_text_sample)
        } else {
            typedArray.getString(R.styleable.EmojiCounterTextViewGroup_customEmoji).orEmpty()
        }
        counter = if (isInEditMode) {
            resources.getString(R.string.counter_text_sample)
        } else {
            typedArray.getString(R.styleable.EmojiCounterTextViewGroup_customCounter).orEmpty()
        }

        internalUpdate()

        val textSize: Float = typedArray.getDimensionPixelSize(
            R.styleable.EmojiCounterTextViewGroup_customTextSize,
            dpToPxFromRes(R.dimen.emoji_counter_custom_text_size).toInt()
        ).toFloat()
        emojiTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        counterTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)

        val textColor = typedArray.getColor(
            R.styleable.EmojiCounterTextViewGroup_customTextColor,
            resources.getColor(R.color.message_text_color)
        )
        counterTextView.setTextColor(textColor)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildWithMargins(emojiTextView, widthMeasureSpec, 0, heightMeasureSpec, 0)
        measureChildWithMargins(
            counterTextView,
            widthMeasureSpec,
            0,
            heightMeasureSpec,
            0
        )

        val contentWidth = emojiTextView.measuredWidth +
                counterTextView.measuredWidth +
                paddingLeft +
                paddingRight +
                counterTextViewPadding
        val contentHeight = maxOf(
            emojiTextView.measuredHeight,
            counterTextView.measuredHeight
        ) + paddingTop + paddingBottom

        setMeasuredDimension(
            resolveSize(contentWidth, widthMeasureSpec),
            resolveSize(contentHeight, heightMeasureSpec)
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        emojiTextView.layout(
            paddingLeft + marginLeft,
            paddingTop,
            emojiTextView.measuredWidth + paddingLeft + marginLeft,
            emojiTextView.measuredHeight + paddingTop
        )

        val counterTextViewLeft = emojiTextView.right
        counterTextView.layout(
            counterTextViewLeft + counterTextViewPadding,
            paddingTop,
            counterTextView.measuredWidth + counterTextViewLeft + counterTextViewPadding,
            counterTextView.measuredHeight + paddingTop
        )
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + SUPPORTED_DRAWABLE_STATE.size)
        if (isSelected) {
            mergeDrawableStates(drawableState, SUPPORTED_DRAWABLE_STATE)
        }
        return drawableState
    }

    override fun dispatchDraw(canvas: Canvas) {
        canvas.drawRoundRect(
            0f,
            0f,
            measuredWidthAsFloat,
            measuredHeightAsFloat,
            dpToPxFromRes(R.dimen.bg_corner_radius),
            dpToPxFromRes(R.dimen.bg_corner_radius),
            bgPaint
        )
        super.dispatchDraw(canvas)
    }

    override fun dispatchSetSelected(selected: Boolean) {
        setSelectedState(selected)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            onClickEvent()
        }
        return true
    }

    fun update(emojiCounter: EmojiCounter) {
        emojiName = emojiCounter.name
        emojiCode = emojiCounter.code
        emoji = emojiCode.getAndroidReadableEmoji()
        counter = emojiCounter.counter.toString()
        isSelected = emojiCounter.isSelected
        internalUpdate()
    }

    private fun internalUpdate() {
        emojiTextView.text = emoji
        counterTextView.text = counter
    }

    private fun onClickEvent() {
        emojiClickListener?.onEmojiClick(emojiName, emojiCode)
    }

    private fun setSelectedState(selected: Boolean) {
        bgPaint.color = if (selected) {
            resources.getColor(R.color.emoji_bg_color_selected)
        } else {
            resources.getColor(R.color.emoji_bg_color_normal)
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

    companion object {
        private val SUPPORTED_DRAWABLE_STATE = intArrayOf(android.R.attr.state_selected)
    }
}