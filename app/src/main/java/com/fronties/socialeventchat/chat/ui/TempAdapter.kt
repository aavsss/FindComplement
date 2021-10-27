package com.fronties.socialeventchat.chat.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fronties.socialeventchat.R
import com.fronties.socialeventchat.chat.model.MessageResponse
import com.fronties.socialeventchat.databinding.ItemUserMessageBinding

class TempAdapter : ListAdapter<MessageResponse, TempAdapter.MessageViewHolder>(MessageDiffCallback()) {

    class MessageViewHolder(
        private val binding: ItemUserMessageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: MessageResponse) {
            binding.message = message
        }
    }

    class MessageDiffCallback() : DiffUtil.ItemCallback<MessageResponse>() {
        override fun areItemsTheSame(oldItem: MessageResponse, newItem: MessageResponse): Boolean {
            return oldItem.senderid == newItem.senderid && oldItem.createdAt == newItem.createdAt
        }

        override fun areContentsTheSame(oldItem: MessageResponse, newItem: MessageResponse): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
//        binding = DataBindingUtil.inflate(
//            LayoutInflater.from(parent.context),
//            R.layout.item_user_message,
//            parent,
//            false
//        )
        val binding = ItemUserMessageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}