package com.thechance.whatschance.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.thechance.whatschance.R
import com.thechance.whatschance.ui.base.BaseAdapter
import com.thechance.whatschance.ui.base.BaseInteractionListener

class ChatAdapter(items: List<MessageUi>, listener: BaseInteractionListener) :
    BaseAdapter<MessageUi>(items, listener) {
    override val layoutID = R.layout.item_chat_sender

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), viewType, parent, false
            )
        )
    }


    override fun getItemViewType(position: Int): Int {
        val position = getItems()[position]
        if (getItems().isNotEmpty()) {
            return if (position.isFromMe) {
                showSenderItem(position)
            } else {
                showReceiverItem(position)
            }
        }
        return R.layout.item_chat_receiver
    }
}

private fun showSenderItem(position: MessageUi): Int {
    return if (checkString(position.textMessage)) {
        R.layout.item_sticker_sender
    } else {
        R.layout.item_chat_sender
    }
}

private fun showReceiverItem(position: MessageUi): Int {
    return if (checkString(position.textMessage)) {
        R.layout.item_sticker_receiver
    } else {
        R.layout.item_chat_receiver
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