package com.apps.hw2.people.item

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.hw2.R

class PeopleItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val name: TextView
        get() = itemView.findViewById(R.id.nameTextView)

    val email: TextView
        get() = itemView.findViewById(R.id.emailTextView)
}