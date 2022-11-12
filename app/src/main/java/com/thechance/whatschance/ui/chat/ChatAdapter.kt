package com.thechance.whatschance.ui.chat

import com.thechance.whatschance.R
import com.thechance.whatschance.ui.base.BaseAdapter
import com.thechance.whatschance.ui.base.BaseInteractionListener

class ChatAdapter(items: List<String>, listener: ChatInteractionListener) :
    BaseAdapter<String>(items, listener) {
    override val layoutID = R.layout.item_chat_sender
}

interface ChatInteractionListener : BaseInteractionListener
