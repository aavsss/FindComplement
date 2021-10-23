package com.fronties.socialeventchat.chat.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fronties.socialeventchat.chat.model.Message
import com.fronties.socialeventchat.databinding.ItemReceivedMessageBinding
import com.fronties.socialeventchat.databinding.ItemUserMessageBinding

class MessageListAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(MessageDiffCallback()) {

    companion object {
        private const val SENT_MESSAGE = 0
        private const val RECEIVED_MESSAGE = 0
    }

    private class MessageDiffCallback : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.uid == newItem.uid && oldItem.timeStamp == newItem.timeStamp
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private class SentMessageHolder(
        private val binding: ItemUserMessageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.message = message
        }
    }

    private class ReceivedMessageHolder(
        private val binding: ItemReceivedMessageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.message = message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SENT_MESSAGE -> SentMessageHolder(
                ItemUserMessageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> ReceivedMessageHolder(
                ItemReceivedMessageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SentMessageHolder -> holder.bind(getItem(position))
            else -> (holder as ReceivedMessageHolder).bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).uid) {
            "uid" -> SENT_MESSAGE //TODO - get user's current UID from Room or a static variable
            else -> RECEIVED_MESSAGE
        }
    }
}
