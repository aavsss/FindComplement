package com.fronties.socialeventchat.event.eventDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fronties.socialeventchat.databinding.EachAttendeeLayoutBinding
import com.fronties.socialeventchat.event.eventList.EventListAdapter
import com.fronties.socialeventchat.event.model.Attendees
import com.fronties.socialeventchat.event.model.SocialEvents

class EventAttendeesAdapter() :
    ListAdapter<Attendees, EventAttendeesAdapter.ViewHolder>(AttendeesDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EachAttendeeLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: EachAttendeeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(attendees: Attendees){
            binding.attendees = attendees
        }

    }

    class AttendeesDiffCallBack: DiffUtil.ItemCallback<Attendees>(){
        override fun areItemsTheSame(oldItem: Attendees, newItem: Attendees): Boolean {
//            TODO("Not yet implemented")
            return oldItem.attendeesName == newItem.attendeesName
        }

        override fun areContentsTheSame(oldItem: Attendees, newItem: Attendees): Boolean {
//            TODO("Not yet implemented")
            return oldItem == newItem
        }
    }

}