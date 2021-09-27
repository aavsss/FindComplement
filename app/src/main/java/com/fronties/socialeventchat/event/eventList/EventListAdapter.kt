package com.fronties.socialeventchat.event.eventList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fronties.socialeventchat.databinding.ItemEventListBinding
import com.fronties.socialeventchat.event.model.SocialEvents

class EventListAdapter(private val clickListener: EventListClickListener) :
    ListAdapter<SocialEvents, EventListAdapter.ViewHolder>(EventListDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemEventListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(private val binding: ItemEventListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: SocialEvents, clickListener: EventListClickListener) {
            binding.event = event
            binding.clickListener = clickListener
        }
    }

    class EventListDiffCallBack : DiffUtil.ItemCallback<SocialEvents>() {
        override fun areItemsTheSame(oldItem: SocialEvents, newItem: SocialEvents): Boolean {
            return oldItem.eid == newItem.eid
        }

        override fun areContentsTheSame(oldItem: SocialEvents, newItem: SocialEvents): Boolean {
            return oldItem == newItem
        }
    }

}


class EventListClickListener(val clickListener: (event: SocialEvents) -> Unit) {
    fun onClick(event: SocialEvents) = clickListener(event)
}