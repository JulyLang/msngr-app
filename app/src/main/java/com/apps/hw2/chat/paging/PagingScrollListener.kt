package com.apps.hw2.chat.paging

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PagingScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val loadNewPageCallback: LoadNewPageCallback
) : RecyclerView.OnScrollListener() {
    private var loading = false
    private var previousTotal = 0
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager.itemCount
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            loading = true
            loadNewPageCallback.loadNewPage()
        }
    }

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }
}