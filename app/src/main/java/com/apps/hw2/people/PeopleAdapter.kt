package com.apps.hw2.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.hw2.R
import com.apps.hw2.people.item.PeopleItem
import com.apps.hw2.people.item.PeopleItemViewHolder

class PeopleAdapter(
    private val peoples: List<PeopleItem>,
) : RecyclerView.Adapter<PeopleItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleItemViewHolder {
        return PeopleItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_people, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PeopleItemViewHolder, position: Int) {
        val item = peoples[position]
        holder.name.text = item.name
        holder.email.text = item.email
    }

    override fun getItemCount(): Int = peoples.size
}