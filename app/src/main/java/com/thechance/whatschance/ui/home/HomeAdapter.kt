package com.thechance.whatschance.ui.home

import com.thechance.whatschance.ui.base.BaseAdapter
import com.thechance.whatschance.ui.base.BaseInteractionListener
import com.thechance.whatschance.R
import com.thechance.whatschance.ui.chat.ChatUi

class HomeAdapter(items: List<ChatUi>, listener: ChatsAdapterListener) :
    BaseAdapter<ChatUi>(items = items, listener = listener) {
    override val layoutID: Int = R.layout.item_chat
}

interface ChatsAdapterListener : BaseInteractionListener