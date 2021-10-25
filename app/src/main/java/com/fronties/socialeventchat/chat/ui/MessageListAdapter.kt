package com.fronties.socialeventchat.chat.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fronties.socialeventchat.chat.model.Message
import com.fronties.socialeventchat.chat.model.MessageResponse
import com.fronties.socialeventchat.databinding.ItemReceivedMessageBinding
import com.fronties.socialeventchat.databinding.ItemUserMessageBinding

class MessageListAdapter : ListAdapter<MessageResponse, RecyclerView.ViewHolder>(MessageDiffCallback()) {

    companion object {
        private const val SENT_MESSAGE = 0
        private const val RECEIVED_MESSAGE = 0
    }

    private class MessageDiffCallback : DiffUtil.ItemCallback<MessageResponse>() {
        override fun areItemsTheSame(oldItem: MessageResponse, newItem: MessageResponse): Boolean {
            return oldItem.senderid == newItem.senderid && oldItem.createdAt == newItem.createdAt
        }

        override fun areContentsTheSame(oldItem: MessageResponse, newItem: MessageResponse): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private class SentMessageHolder(
        private val binding: ItemUserMessageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: MessageResponse) {
            binding.message = message
        }
    }

    private class ReceivedMessageHolder(
        private val binding: ItemReceivedMessageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: MessageResponse) {
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
        return when (getItem(position).senderid) {
            "uid" -> SENT_MESSAGE //TODO - get user's current UID from Room or a static variable
            else -> RECEIVED_MESSAGE
        }
    }
}
