package com.thechance.whatschance.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.thechance.whatschance.R
import com.thechance.whatschance.ui.base.BaseAdapter
import com.thechance.whatschance.ui.base.BaseInteractionListener
import java.text.FieldPosition

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
                showSenderItem(position.textMessage)
            } else {
                showReceiverItem(position.textMessage)
            }
        }
        return R.layout.item_chat_receiver
    }
}

private fun showSenderItem(message: String): Int {
    val chat = ChatLayout(
        message = message,
        stickerLayout = R.layout.item_sticker_sender,
        messageLayout = R.layout.item_chat_sender
    )
    return checkIfMessageOrSticker(chat)
}

private fun showReceiverItem(message: String): Int {
    val chat = ChatLayout(
        message = message,
        stickerLayout = R.layout.item_sticker_receiver,
        messageLayout = R.layout.item_chat_receiver
    )
    return checkIfMessageOrSticker(chat)
}

private fun checkIfMessageOrSticker(chat: ChatLayout): Int {
    return if (checkString(chat.message)) {
        chat.stickerLayout
    } else {
        chat.messageLayout
    }
}

data class ChatLayout(
    val message: String,
    val stickerLayout: Int,
    val messageLayout: Int
)

private fun checkString(message: String): Boolean {
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