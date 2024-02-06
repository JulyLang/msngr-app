package com.apps.hw2.channels.pager

import android.os.Bundle
import com.apps.hw2.app.App
import com.apps.hw2.channels.pager.stream.StreamItemRecyclerViewAdapter
import com.apps.hw2.channels.pager.usecase.ISearchStreamsUseCase
import com.apps.hw2.channels.pager.usecase.ObserveAllStreamsUseCase
import com.apps.hw2.channels.pager.usecase.SyncAllStreamsUseCase
import javax.inject.Inject

class AllStreamsPageFragment : BasePageFragment<StreamItemRecyclerViewAdapter>() {

    override val logTag = "AllStreamsPageFragment"

    @Inject
    override lateinit var searchStreamsUseCase: ISearchStreamsUseCase

    @Inject
    override lateinit var observeStreamsUseCase: ObserveAllStreamsUseCase

    @Inject
    override lateinit var syncStreamsUseCase: SyncAllStreamsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).channelsComponent.injectAllStreamsPageFragment(this)
    }
}