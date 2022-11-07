package com.thechance.whatschance.ui.home

import com.thechance.whatschance.BaseAdapter
import com.thechance.whatschance.BaseInteractionListener
import com.thechance.whatschance.R

class HomeAdapter(items: List<Chat>, listener: ChatsAdapterListener) :
    BaseAdapter<Chat>(items = items, listener = listener) {
    override val layoutID: Int = R.layout.item_chat
}

data class Chat(
    val id: String,
    val name: String,
    val lastMessage: String,
    val lastMessageTime: String,
)

interface ChatsAdapterListener : BaseInteractionListener