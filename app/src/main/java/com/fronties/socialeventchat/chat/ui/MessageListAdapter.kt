package com.fronties.socialeventchat.chat.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fronties.socialeventchat.chat.model.MessageResponse
import com.fronties.socialeventchat.databinding.ItemReceivedMessageBinding
import com.fronties.socialeventchat.databinding.ItemUserMessageBinding

class MessageListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val SENT_MESSAGE = 0
        private const val RECEIVED_MESSAGE = 0
    }

    private val messageDiffCallback = object : DiffUtil.ItemCallback<MessageResponse>() {
        override fun areItemsTheSame(oldItem: MessageResponse, newItem: MessageResponse): Boolean {
            return oldItem.senderid == newItem.senderid && oldItem.createdAt == newItem.createdAt
        }

        override fun areContentsTheSame(oldItem: MessageResponse, newItem: MessageResponse): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, messageDiffCallback)
    var chats: List<MessageResponse>
        get() = differ.currentList
        set(value) = differ.submitList(value)

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
            is SentMessageHolder -> holder.bind(chats[position])
            else -> (holder as ReceivedMessageHolder).bind(chats[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (chats[position].senderid) {
            "5" -> SENT_MESSAGE // TODO - get user's current UID from Room or a static variable
            else -> RECEIVED_MESSAGE
        }
    }

    override fun getItemCount(): Int {
        return chats.size
    }
}
