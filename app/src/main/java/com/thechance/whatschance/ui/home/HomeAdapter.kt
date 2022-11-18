package com.thechance.whatschance.ui.home

import com.thechance.whatschance.R
import com.thechance.whatschance.ui.base.BaseAdapter
import com.thechance.whatschance.ui.base.BaseInteractionListener

class HomeAdapter(items: List<FriendsUIState>, listener: ChatsAdapterListener) :
    BaseAdapter<FriendsUIState>(items = items, listener = listener) {
    override val layoutID: Int = R.layout.item_chat
}

interface ChatsAdapterListener : BaseInteractionListener {
    fun onFriendSelected(friend: FriendsUIState)
}