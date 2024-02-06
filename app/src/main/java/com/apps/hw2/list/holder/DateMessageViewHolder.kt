package com.apps.hw2.list.holder

import android.text.format.DateFormat
import android.view.View
import android.widget.TextView
import com.apps.hw2.R
import com.apps.hw2.list.model.DateMessage
import java.util.*

class DateMessageViewHolder (
    itemView: View
) : BaseMessageViewHolder<DateMessage>(itemView) {

    private val timeTexView: TextView
        get() = itemView.findViewById(R.id.timeTextView)

    override fun render(message: DateMessage, payloads: List<Any>) {
        super.render(message, payloads)
        timeTexView.text = getDate(message.dateCreated)
    }

    private fun getDate(timestamp: Long): String {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = timestamp
        return DateFormat.format("dd MMMM", calendar).toString()
    }
}