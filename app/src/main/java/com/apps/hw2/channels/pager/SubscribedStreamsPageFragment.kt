package com.apps.hw2.channels.pager

import android.os.Bundle
import com.apps.hw2.app.App
import com.apps.hw2.channels.pager.stream.StreamItemRecyclerViewAdapter
import com.apps.hw2.channels.pager.usecase.ISearchStreamsUseCase
import com.apps.hw2.channels.pager.usecase.ObserveSubscribedStreamsUseCase
import com.apps.hw2.channels.pager.usecase.SyncSubscribedStreamsUseCase
import javax.inject.Inject

class SubscribedStreamsPageFragment : BasePageFragment<StreamItemRecyclerViewAdapter>() {

    override val logTag = "SubscribedStreamsPageFragment"

    @Inject
    override lateinit var searchStreamsUseCase: ISearchStreamsUseCase

    @Inject
    override lateinit var observeStreamsUseCase: ObserveSubscribedStreamsUseCase

    @Inject
    override lateinit var syncStreamsUseCase: SyncSubscribedStreamsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).channelsComponent.injectSubscribedStreamsPageFragment(this)
    }
}