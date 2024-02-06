package com.apps.hw2

import android.view.View
import com.apps.hw2.channels.pager.SubscribedStreamsPageFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object SubscribedStreamsPageScreen : KScreen<SubscribedStreamsPageScreen>() {
    override val layoutId: Int = R.layout.fragment_page_list
    override val viewClass: Class<*> = SubscribedStreamsPageFragment::class.java

    val topicsList = KRecyclerView({ withId(R.id.list) }, { itemType { StreamItem(it) } })

    class StreamItem(parent: Matcher<View>) : KRecyclerItem<StreamItem>(parent) {
        val title = KTextView(parent) { withId(R.id.nameTextView) }
    }
}