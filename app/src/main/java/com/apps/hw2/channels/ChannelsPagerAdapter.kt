package com.apps.hw2.channels

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.apps.hw2.channels.pager.AllStreamsPageFragment
import com.apps.hw2.channels.pager.SubscribedStreamsPageFragment

class ChannelsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SubscribedStreamsPageFragment()
            else -> AllStreamsPageFragment()
        }
    }
}