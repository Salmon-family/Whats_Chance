package com.thechance.whatschance.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.thechance.whatschance.ui.base.BaseAdapter
import com.thechance.whatschance.ui.base.BaseInteractionListener
import com.thechance.whatschance.R

class ChatAdapter(items: List<DetailsChatUiState>, listener: ChatsAdapterListener) :
    BaseAdapter<DetailsChatUiState>(items = items, listener = listener) {
    override val layoutID: Int = R.layout.item_chat_sender

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), viewType, parent, false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        if (getItems().isNotEmpty()) {
            return if (checkString(getItems()[position].lastMessage)) {
                R.layout.item_sticker
            } else {
                R.layout.item_chat_sender
            }
        }
        return R.layout.item_chat_sender
    }
}


fun checkString(message: String): Boolean {
    val stickers = listOf(
        ":happy:",
        ":angry:",
        ":cry:",
        ":laugh:",
        ":love:",
        ":smile:",
        ":unhappy:",
        ":wink:",
        ":wow:",
        ":sad:",
    )
    return message in stickers
}

interface ChatsAdapterListener : BaseInteractionListener