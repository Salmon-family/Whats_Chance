package com.thechance.whatschance.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.thechance.whatschance.BR
import com.thechance.whatschance.R
import com.thechance.whatschance.ui.base.BaseAdapter
import com.thechance.whatschance.ui.base.BaseInteractionListener

class ChatUIStateAdapter(
    private var item: List<ChatItemUIState>,
    private var listener: BaseInteractionListener
) : BaseAdapter<ChatItemUIState>(item, listener) {
    override val layoutID: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bind(holder as ItemViewHolder, position)
    }

    override fun bind(holder: ItemViewHolder, position: Int) {
        when (val currentItem = item[position]) {
            is ChatItemUIState.DateText -> {}
            is ChatItemUIState.TodayMessage -> {
                holder.binding.run {
                    setVariable(BR.adapterRecycler, ChatAdapter(currentItem.data, listener))
                }
            }
        }
    }

    override fun setItems(newItems: List<ChatItemUIState>) {
        item = newItems.sortedBy { it.priority }
        super.setItems(newItems)
    }

    override fun areContentSame(oldItem: ChatItemUIState, newItem: ChatItemUIState): Boolean {
        return oldItem.priority == newItem.priority
    }

    override fun getItemViewType(position: Int): Int {
        return when (item[position]) {
            is ChatItemUIState.DateText -> R.layout.item_chat_date
            is ChatItemUIState.TodayMessage -> R.layout.list_messges
        }
    }
}
