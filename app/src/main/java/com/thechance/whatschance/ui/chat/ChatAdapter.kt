package com.thechance.whatschance.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.thechance.whatschance.R
import com.thechance.whatschance.ui.base.BaseAdapter
import com.thechance.whatschance.ui.base.BaseInteractionListener

class ChatAdapter(items:List<MessageUi>,listener: BaseInteractionListener) : BaseAdapter<MessageUi>(items,listener) {
    override val layoutID = R.layout.item_chat_sender

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), viewType, parent, false
            )
        )
    }


    override fun getItemViewType(position: Int): Int {
        if (getItems().isNotEmpty()) {
            return if (getItems()[position].isFromMe) {
                R.layout.item_chat_sender
            } else {
                R.layout.item_chat_receiver
            }
        }
        return R.layout.item_chat_receiver
    }
}