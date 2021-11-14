package com.fronties.socialeventchat.event.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.databinding.ItemEventListBinding
import com.fronties.socialeventchat.event.eventList.EventListFragmentDirections
import com.fronties.socialeventchat.event.model.EventType
import com.fronties.socialeventchat.event.model.SocialEvents
import java.util.*
import kotlin.collections.ArrayList

class AttendingEventsAdapter() :
    ListAdapter<SocialEvents, AttendingEventsAdapter.ViewHolder>(EventListDiffCallBack()), Filterable {

    var eventFilterList: MutableList<SocialEvents>? = this.currentList
    var allEventList: MutableList<SocialEvents> = this.currentList

    override fun onCurrentListChanged(
        previousList: MutableList<SocialEvents>,
        currentList: MutableList<SocialEvents>
    ) {
        eventFilterList = currentList
        allEventList = currentList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemEventListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(eventFilterList!![position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                eventFilterList = if (charSearch.isEmpty()) {
                    allEventList
                } else {
                    val resultList = ArrayList<SocialEvents>()
                    for (row in allEventList) {
                        if (row.name?.lowercase(Locale.ROOT)!!.contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = eventFilterList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                eventFilterList = p1?.values as MutableList<SocialEvents>?
                notifyDataSetChanged()
            }
        }
    }

    class ViewHolder(
        private val binding: ItemEventListBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: SocialEvents) {
            binding.event = event
            binding.root.setOnClickListener {
                val bundle = bundleOf(
                    "eventId" to event.eid!!,
                    "isAttending" to EventType.ATTENDED.value
                )
                it.findNavController().navigate(
                    R.id.action_eventListFragment_to_eventDetailFragment,
                    bundle
                )
            }
            binding.btnJoin.text = "See Chat"
        }
    }

    override fun getItemCount(): Int {
        return eventFilterList!!.size
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
