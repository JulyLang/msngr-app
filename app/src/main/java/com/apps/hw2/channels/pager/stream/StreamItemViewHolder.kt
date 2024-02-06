package com.apps.hw2.channels.pager.stream

import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.TextView
import com.apps.hw2.R

class StreamItemViewHolder(itemView: View) : BaseViewHolder<BindStreamItemArgs>(itemView) {

    private val name: TextView
        get() = itemView.findViewById(R.id.nameTextView)

    val arrow: View
        get() = itemView.findViewById(R.id.arrowDownImageView)

    private var streamClickListener: StreamClickListener? = null

    init {
        itemView.setOnClickListener{streamClickListener?.onClick(it)}
    }

    override fun bind(args: BindStreamItemArgs) {
        name.text = args.name
        arrow.rotation = if (args.isExpanded) IC_EXPANDED_ROTATION_DEG else IC_COLLAPSED_ROTATION_DEG
        arrow.visibility = if(args.topicsCounter > 0) VISIBLE else INVISIBLE
        streamClickListener = args.streamClickListener
    }

    companion object {
        private const val IC_EXPANDED_ROTATION_DEG = 0F
        private const val IC_COLLAPSED_ROTATION_DEG = 180F
    }
}