package com.thechance.whatschance.ui.home

import com.thechance.whatschance.BaseAdapter
import com.thechance.whatschance.BaseInteractionListener
import com.thechance.whatschance.R

class HomeAdapter(items: List<ChatUiState>, listener: ChatsAdapterListener) :
    BaseAdapter<ChatUiState>(items = items, listener = listener) {
    override val layoutID: Int = R.layout.item_chat
}

interface ChatsAdapterListener : BaseInteractionListener