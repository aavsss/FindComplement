package com.fronties.socialeventchat.chat.ui

import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fronties.socialeventchat.chat.ui.MessageListAdapter

class ScrollToBottomObserver(
    private val recyclerView: RecyclerView,
    private val adapter: MessageListAdapter, // TODO: change to whichever adapter is added later
    private val manager: LinearLayoutManager
) : RecyclerView.AdapterDataObserver() {
    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        super.onItemRangeInserted(positionStart, itemCount)
        val count = adapter.itemCount
        val lastVisiblePosition = manager.findLastCompletelyVisibleItemPosition()
        // If the recycler view is initially being loaded or the
        // user is at the bottom of the list, scroll to the bottom
        // of the list to show the newly added message.
        val loading = lastVisiblePosition == -1
        val atBottom = positionStart >= count - 1 && lastVisiblePosition == positionStart - 1
        if (loading || atBottom) {
            recyclerView.scrollToPosition(positionStart)
        }
    }
}