package com.apps.hw2.channels.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apps.hw2.R
import com.apps.hw2.channels.pager.elm.*
import com.apps.hw2.channels.pager.stream.ExpandableStreamItemAnimator
import com.apps.hw2.channels.pager.stream.StreamItemDecorator
import com.apps.hw2.channels.pager.stream.StreamItemRecyclerViewAdapter
import com.apps.hw2.channels.pager.stream.TopicClickListener
import com.apps.hw2.channels.pager.usecase.IObserveStreamsUseCase
import com.apps.hw2.channels.pager.usecase.ISearchStreamsUseCase
import com.apps.hw2.channels.pager.usecase.ISyncStreamsUseCase
import com.apps.hw2.chat.ChatFragment
import vivid.money.elmslie.android.base.ElmFragment
import vivid.money.elmslie.core.ElmStoreCompat
import vivid.money.elmslie.core.store.Store

abstract class BasePageFragment<Adapter> : ElmFragment<Event, Effect, State>(), TopicClickListener
        where Adapter : RecyclerView.Adapter<out RecyclerView.ViewHolder> {

    private val layout = R.layout.fragment_page_list

    private val recyclerView: RecyclerView
        get() = requireView().findViewById(R.id.list)

    protected abstract val logTag: String

    abstract val searchStreamsUseCase: ISearchStreamsUseCase
    abstract val observeStreamsUseCase: IObserveStreamsUseCase
    abstract val syncStreamsUseCase: ISyncStreamsUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.itemAnimator = ExpandableStreamItemAnimator()
        recyclerView.addItemDecoration(StreamItemDecorator())
    }

    override fun onTopicClicked(streamId: Int, topicName: String) {
        store.accept(Event.Ui.OnTopicClicked(streamId, topicName))
    }

    override val initEvent: Event = Event.Ui.Init

    override fun createStore(): Store<Event, Effect, State> {
        return ElmStoreCompat(
            initialState = State(),
            reducer = Reducer(),
            actor = Actor(searchStreamsUseCase, observeStreamsUseCase, syncStreamsUseCase, topicClickListener = this)
        )
    }

    override fun render(state: State) {
        recyclerView.adapter = ConcatAdapter(
            concatAdapterConfig, filterStreamsBySearch(
                streams = state.streams,
                searchQuery = state.searchQuery
            )
        )
    }

    private fun filterStreamsBySearch(
        streams: List<StreamItemRecyclerViewAdapter>,
        searchQuery: String
    ): List<StreamItemRecyclerViewAdapter> {
        if (searchQuery.isEmpty()) return streams
        return streams.filter { it.isFindByInput(input = searchQuery) }
    }

    override fun handleEffect(effect: Effect): Unit? = when (effect) {
        is Effect.NavigateToChat -> {
            val fragment = ChatFragment.newInstance(effect.streamId, effect.topicName)
            requireParentFragment().parentFragmentManager.beginTransaction()
                .replace(R.id.contentFragmentView, fragment, ChatFragment.TAG)
                .addToBackStack(null)
                .commit()
            Unit
        }
        is Effect.ShowError -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val concatAdapterConfig = ConcatAdapter.Config.Builder()
            .setIsolateViewTypes(false)
            .build()
    }
}