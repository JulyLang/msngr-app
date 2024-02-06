package com.apps.hw2.channels.di

import com.apps.hw2.channels.pager.AllStreamsPageFragment
import com.apps.hw2.channels.pager.SubscribedStreamsPageFragment
import com.apps.hw2.di.ApplicationModule
import com.apps.hw2.di.DatabaseModule
import com.apps.hw2.di.NetworkModule
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        ChannelsModule::class
    ]
)
@ChannelsScope
interface ChannelsComponent {
    fun injectSubscribedStreamsPageFragment(subscribedStreamsPageFragment: SubscribedStreamsPageFragment)
    fun injectAllStreamsPageFragment(allStreamsPageFragment: AllStreamsPageFragment)
}