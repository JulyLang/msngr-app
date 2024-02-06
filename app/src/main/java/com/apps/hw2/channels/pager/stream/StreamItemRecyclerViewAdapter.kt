package com.apps.hw2.channels.pager.stream

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.hw2.R
import kotlin.properties.Delegates

class StreamItemRecyclerViewAdapter(
    private val streamItem: IStreamItem,
    private val topicClickListener: TopicClickListener
) : RecyclerView.Adapter<BaseViewHolder<out BindViewHolderArgs>>() {

    private var isExpanded: Boolean by Delegates.observable(false) { _, _, newExpandedValue: Boolean ->
        if (newExpandedValue) {
            notifyItemRangeInserted(1, streamItem.topics.size)
            notifyItemChanged(0)
        } else {
            notifyItemRangeRemoved(1, streamItem.topics.size)
            notifyItemChanged(0)
        }
    }

    fun isFindByInput(input: String): Boolean {
        return streamItem.isFindByInput(input)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<out BindViewHolderArgs> {
        return when(viewType) {
            TYPE_STREAM -> StreamItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_stream, parent, false)
            )
            TYPE_SHIMMER_STREAM -> ShimmerStreamItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_shimmer_stream, parent, false)
            )
            else -> TopicItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_sub_stream, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<out BindViewHolderArgs>, position: Int) {
        when (holder) {
            is StreamItemViewHolder -> holder.bind(
                BindStreamItemArgs(
                    streamItem.name,
                    isExpanded,
                    streamItem.topics.size,
                    streamClickListener)
            )
            is TopicItemViewHolder -> holder.bind(
                BindTopicsItemArgs(
                    streamItem.topics[position - 1].streamId,
                    streamItem.topics[position - 1].topicName,
                    streamItem.topics[position - 1].messagesCount,
                    position - 1,
                    topicClickListener
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            if (streamItem is ShimmerStreamItem) TYPE_SHIMMER_STREAM else TYPE_STREAM
        } else {
            TYPE_SUB_STREAM
        }
    }

    override fun getItemCount(): Int = if (isExpanded) streamItem.topics.size + 1 else 1

    private val streamClickListener = object : StreamClickListener {
        override fun onClick(v: View) {
            isExpanded = !isExpanded
        }
    }

    companion object {
        private const val TYPE_STREAM = 0
        private const val TYPE_SUB_STREAM = 1
        private const val TYPE_SHIMMER_STREAM = 2
    }
}