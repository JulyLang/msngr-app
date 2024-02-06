package com.apps.hw2.people

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apps.hw2.R
import com.apps.hw2.people.item.PeoplesStub

class PeopleFragment : Fragment() {

    private val recyclerView: RecyclerView
        get() = requireView().findViewById(R.id.list)
    private val adapter = PeopleAdapter(
        PeoplesStub.peoples
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_people, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(PeopleItemDecorator())
        adapter.notifyDataSetChanged()
    }

    companion object {
        const val TAG = "PeopleFragment"
    }
}