package com.apps.hw2.channels.pager.stream

import android.view.View
import android.view.View.INVISIBLE
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.apps.hw2.R

class TopicItemViewHolder(itemView: View) : BaseViewHolder<BindTopicsItemArgs>(itemView) {

    private val name: TextView
        get() = itemView.findViewById(R.id.nameTextView)

    private val count: TextView
        get() = itemView.findViewById(R.id.countTextView)

    private var topicClickListener: TopicClickListener? = null

    private var streamId: Int = -1
    private var topicName: String = ""

    init {
        itemView.setOnClickListener { topicClickListener?.onTopicClicked(streamId, topicName) }
    }

    override fun bind(args: BindTopicsItemArgs) {
        name.text = args.topicName
        if (args.counter <= 0) count.visibility = INVISIBLE else count.text =
            itemView.context.getString(R.string.messages_count, args.counter)
        itemView.setBackgroundColor(
            ContextCompat.getColor(itemView.context, getBgColorByPosition(args.pos))
        )
        topicClickListener = args.topicClickListener
        streamId = args.streamId
        topicName = args.topicName
    }

    companion object {
        private fun getBgColorByPosition(pos: Int): Int {
            return if (pos % 2 == 0) {
                R.color.sub_stream_bg_green
            } else {
                R.color.sub_stream_bg_yellow
            }
        }
    }
}