package com.apps.hw2.list.holder

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.apps.hw2.list.model.BaseMessage

abstract class BaseMessageViewHolder<M>(itemView: View) :
    RecyclerView.ViewHolder(itemView) where M : BaseMessage {

    protected var baseMessage: M? = null

    @CallSuper
    open fun render(message: M, payloads: List<Any>) {
        baseMessage = message
    }
}