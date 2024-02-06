package com.apps.hw2.channels

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.apps.hw2.R
import com.apps.hw2.channels.pager.stream.helper.StreamsSearchHelper
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ChannelsFragment : Fragment(), SearchView.OnQueryTextListener {

    private val tabs: TabLayout
        get() = requireView().findViewById(R.id.tabs)
    private val pager: ViewPager2
        get() = requireView().findViewById(R.id.pager)

    private lateinit var adapter: ChannelsPagerAdapter

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.appbar_search_menu, menu)
        val searchMenuItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_channels, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ChannelsPagerAdapter(this)
        pager.adapter = adapter

        TabLayoutMediator(tabs, pager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.subscribed)
                else -> getString(R.string.all_streams)
            }
        }.attach()
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        Log.i(TAG, "onQueryTextSubmit query=$query")
        onQueryTextReceived(input = query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Log.i(TAG, "onQueryTextChange newText=$newText")
        onQueryTextReceived(input = newText)
        return true
    }

    private fun onQueryTextReceived(input: String) {
        Log.i(TAG, "onQueryTextReceived input=$input")
        StreamsSearchHelper.onQueryTextReceived(input)
    }

    companion object {
        const val TAG = "ChannelsFragment"
    }
}