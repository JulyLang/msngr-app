package com.apps.hw2.channels.pager.stream

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<Args>(itemView: View) : RecyclerView.ViewHolder(itemView)
        where Args : BindViewHolderArgs {

    abstract fun bind(args: Args)
}